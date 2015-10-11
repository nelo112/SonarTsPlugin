package com.pablissimo.sonar.rules;

import com.pablissimo.sonar.TypeScriptLanguage;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.server.rule.RulesDefinition;

public class TsRulesDefinition implements RulesDefinition {
    public static final String REPOSITORY_NAME = "tslint";

    public void define(Context context) {
        NewRepository repository =
            context
                .createRepository(REPOSITORY_NAME, TypeScriptLanguage.LANGUAGE_EXTENSION)
                .setName("TsLint Analyser");

        for (TsLintRule rule : TsLintRule.values()) {
            repository.createRule(rule.getKey())
                .setName(rule.getTitle())
                .setSeverity(rule.getDefaultSeverity());
        }

        for (NewRule rule : repository.rules()) {
            rule.setHtmlDescription("HTML description to follow");
            rule.setStatus(RuleStatus.READY);
        }
        repository.done();
    }
}
