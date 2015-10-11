package com.pablissimo.sonar;

import com.pablissimo.sonar.rules.TsLintRule;
import com.pablissimo.sonar.rules.TsRulesDefinition;
import org.junit.Before;
import org.junit.Test;
import org.sonar.api.rule.Severity;
import org.sonar.api.server.rule.RulesDefinition.Context;
import org.sonar.api.server.rule.RulesDefinition.Rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class TsRulesDefinitionTest {

    TsRulesDefinition definition;
    Context context;

    @Before
    public void setUp() throws Exception {
        this.definition = new TsRulesDefinition();
        this.context = new Context();
    }

    @Test
    public void CreatesRepository() {
        Context context = mock(Context.class, RETURNS_DEEP_STUBS);
        this.definition.define(context);
        verify(context).createRepository(eq(TsRulesDefinition.REPOSITORY_NAME), eq(TypeScriptLanguage.LANGUAGE_EXTENSION));
    }

    @Test
    public void configuresAllRules() {
        this.definition.define(context);
        for (TsLintRule rule : TsLintRule.values()) {
            Rule definedRule = this.context
                .repository(TsRulesDefinition.REPOSITORY_NAME)
                .rule(rule.getKey());
            assertNotNull(definedRule);
            assertEquals(definedRule.name(), rule.getTitle());
            assertEquals(definedRule.severity(), rule.getDefaultSeverity());
        }
    }

}
