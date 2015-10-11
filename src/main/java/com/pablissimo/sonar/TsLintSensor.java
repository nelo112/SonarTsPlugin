package com.pablissimo.sonar;

import com.google.common.io.Files;
import com.pablissimo.sonar.model.TsLintIssue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.issue.Issuable;
import org.sonar.api.issue.Issue;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.rules.ActiveRule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class TsLintSensor implements Sensor {

    private static final Logger LOG = LoggerFactory.getLogger(TsLintExecutorImpl.class);

    private Settings settings;
    private FileSystem fileSystem;
    private FilePredicates filePredicates;
    private ResourcePerspectives perspectives;
    private Set<String> activeRuleKeys = new HashSet<>();

    public TsLintSensor(Settings settings, FileSystem fileSystem, ResourcePerspectives perspectives, RulesProfile rulesProfile) {
        this.settings = settings;
        this.fileSystem = fileSystem;
        this.filePredicates = fileSystem.predicates();
        this.perspectives = perspectives;

        for (ActiveRule rule : rulesProfile.getActiveRulesByRepository(TsRulesDefinition.REPOSITORY_NAME)) {
            this.activeRuleKeys.add(rule.getRuleKey());
        }
    }

    public boolean shouldExecuteOnProject(Project project) {
        boolean toReturn = hasFilesToAnalyze();

        return toReturn;
    }

    private boolean hasFilesToAnalyze() {
        return fileSystem.files(this.filePredicates.hasLanguage(TypeScriptLanguage.LANGUAGE_EXTENSION)).iterator().hasNext();
    }

    public void analyse(Project project, SensorContext context) {

        String pathToTsLint = settings.getString(TypeScriptPlugin.SETTING_TS_LINT_PATH);
        if (pathToTsLint == null) {
            LOG.warn("Path to tslint (" + TypeScriptPlugin.SETTING_TS_LINT_PATH + ") is not defined. Skipping tslint analysis.");
            return;
        }
        String pathToTsLintConfig = settings.getString(TypeScriptPlugin.SETTING_TS_LINT_CONFIG_PATH);
        if (pathToTsLintConfig == null) {
            LOG.warn("Path to tslint config(" + TypeScriptPlugin.SETTING_TS_LINT_PATH + ") is not defined. Skipping tslint analysis.");
            return;
        }

        TsLintExecutor executor = this.getTsLintExecutor();
        TsLintParser parser = this.getTsLintParser();

        boolean skipTypeDefFiles = settings.getBoolean(TypeScriptPlugin.SETTING_EXCLUDE_TYPE_DEFINITION_FILES);

        for (File file : fileSystem.files(this.filePredicates.hasLanguage(TypeScriptLanguage.LANGUAGE_EXTENSION))) {
            if (skipTypeDefFiles && file.getName().toLowerCase().endsWith("." + TypeScriptLanguage.LANGUAGE_DEFINITION_EXTENSION)) {
                continue;
            }

            Resource resource = this.getFileFromIOFile(file, project);
            Issuable issuable = perspectives.as(Issuable.class, resource);

            String jsonResult = executor.execute(pathToTsLint, pathToTsLintConfig, file.getAbsolutePath());

            TsLintIssue[] issues = parser.parse(jsonResult);

            if (issues != null) {
                for (TsLintIssue issue : issues) {
                    String tslintRuleName = issue.getRuleName();
                    if (!activeRuleKeys.contains(tslintRuleName)) {
                        continue;
                    }

                    Issue convertedIssue = issuable
                        .newIssueBuilder()
                        .line(issue.getStartPosition().getLine() + 1)
                        .message(issue.getFailure())
                        .ruleKey(RuleKey.of(TsRulesDefinition.REPOSITORY_NAME, issue.getRuleName()))
                        .build();

                    issuable.addIssue(convertedIssue);
                }
            }
        }
    }

    protected org.sonar.api.resources.File getFileFromIOFile(File file, Project project) {
        return org.sonar.api.resources.File.fromIOFile(file, project);
    }

    protected void writeConfiguration(String configSerialised, File configFile, Charset encoding) throws IOException {
        Files.write(configSerialised, configFile, encoding);
    }

    protected TsLintExecutor getTsLintExecutor() {
        return new TsLintExecutorImpl();
    }

    protected TsLintParser getTsLintParser() {
        return new TsLintParserImpl();
    }

}
