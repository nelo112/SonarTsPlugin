package com.pablissimo.sonar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.issue.Issuable;
import org.sonar.api.issue.Issuable.IssueBuilder;
import org.sonar.api.issue.Issue;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.Project;

import com.pablissimo.sonar.model.TsLintIssue;
import com.pablissimo.sonar.model.TsLintPosition;

import edu.emory.mathcs.backport.java.util.Arrays;

public class TsLintSensorTest {

    Settings settings;
    FileSystem fileSystem;
    ResourcePerspectives perspectives;
    RulesProfile rulesProfile;
    FilePredicates filePredicates;
    FilePredicate predicate;
    Issuable issuable;
    IssueBuilder issueBuilder;

    List<File> files;
    File file;
    org.sonar.api.resources.File sonarFile;

    TsLintExecutor executor;
    TsLintParser parser;
    TsLintSensor sensor;

    @Before
    public void setUp() throws Exception {
        this.settings = mock(Settings.class);
        when(this.settings.getString(TypeScriptPlugin.SETTING_TS_LINT_PATH)).thenReturn("/path/to/tslint");

        this.fileSystem = mock(FileSystem.class);
        this.perspectives = mock(ResourcePerspectives.class);
        this.issuable = mock(Issuable.class);
        this.issueBuilder = mock(IssueBuilder.class, RETURNS_DEEP_STUBS);
        when(this.issuable.newIssueBuilder()).thenReturn(this.issueBuilder);
        doReturn(this.issuable).when(this.perspectives).as(eq(Issuable.class), any(org.sonar.api.resources.File.class));
        this.rulesProfile = mock(RulesProfile.class);

        this.file = mock(File.class);
        doReturn(true).when(this.file).isFile();

        this.files = new ArrayList<File>(Arrays.asList(new File[]{
            this.file
        }));

        this.fileSystem = mock(FileSystem.class);
        this.predicate = mock(FilePredicate.class);
        when(fileSystem.files(this.predicate)).thenReturn(this.files);

        this.filePredicates = mock(FilePredicates.class);
        when(this.fileSystem.predicates()).thenReturn(this.filePredicates);
        when(filePredicates.hasLanguage(TypeScriptLanguage.LANGUAGE_EXTENSION)).thenReturn(this.predicate);

        this.sonarFile = mock(org.sonar.api.resources.File.class);

        this.executor = mock(TsLintExecutor.class);
        this.parser = mock(TsLintParser.class);
        this.sensor = spy(new TsLintSensor(settings, fileSystem, perspectives, rulesProfile));
        doNothing().when(this.sensor).writeConfiguration(any(String.class), any(File.class), any(Charset.class));
        doReturn(this.sonarFile).when(this.sensor).getFileFromIOFile(eq(this.file), any(Project.class));
        doReturn(this.executor).when(this.sensor).getTsLintExecutor();
        doReturn(this.parser).when(this.sensor).getTsLintParser();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldExecuteOnProject_ReturnsTrue_WhenAnyTsFiles() {
        assertTrue(this.sensor.shouldExecuteOnProject(null));
    }

    @Test
    public void shouldExecuteOnProject_ReturnsFalse_WhenNoTsFiles() {
        when(fileSystem.files(this.predicate)).thenReturn(new ArrayList<File>());
        assertFalse(this.sensor.shouldExecuteOnProject(null));
    }

    @Test
    public void analyse_WritesConfigurationFile() throws IOException {
        when(this.fileSystem.files(any(FilePredicate.class))).thenReturn(new ArrayList<File>());
        this.sensor.analyse(mock(Project.class), mock(SensorContext.class));

        ArgumentCaptor<File> fileCaptor = ArgumentCaptor.forClass(File.class);
        verify(this.sensor).writeConfiguration(eq("{\n  \"rules\": {}\n}"), fileCaptor.capture(), eq(Charsets.UTF_8));

        assertEquals("tslint.json", fileCaptor.getValue().getName());
    }

    @Test
    public void analyse_addsIssues() {
        TsLintIssue issue = new TsLintIssue();
        issue.setFailure("failure");
        issue.setRuleName("rule name");

        TsLintPosition startPosition = new TsLintPosition();
        startPosition.setLine(1);

        issue.setStartPosition(startPosition);

        TsLintIssue[] issues = new TsLintIssue[]{
            issue
        };

        final List<Issue> capturedIssues = new ArrayList<Issue>();
        Answer<Void> captureIssue = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                capturedIssues.add((Issue) invocation.getArguments()[0]);
                return null;
            }
        };

        when(this.issuable.addIssue(any(Issue.class))).then(captureIssue);
        when(parser.parse(any(String.class))).thenReturn(issues);
        this.sensor.analyse(mock(Project.class), mock(SensorContext.class));

        assertEquals(1, capturedIssues.size());
    }

    @Test
    public void analyse_doesNothingWhenNotConfigured() throws IOException {
        when(this.settings.getString(TypeScriptPlugin.SETTING_TS_LINT_PATH)).thenReturn(null);

        when(this.fileSystem.files(any(FilePredicate.class))).thenReturn(new ArrayList<File>());
        this.sensor.analyse(mock(Project.class), mock(SensorContext.class));

        verify(this.sensor, never()).writeConfiguration(anyString(), any(File.class), any(Charset.class));
        verify(this.issuable, never()).addIssue(any(Issue.class));
    }
}
