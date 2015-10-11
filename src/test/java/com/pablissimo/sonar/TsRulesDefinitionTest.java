package com.pablissimo.sonar;

import org.junit.Before;
import org.junit.Test;
import org.sonar.api.rule.Severity;
import org.sonar.api.server.rule.RulesDefinition.Context;
import org.sonar.api.server.rule.RulesDefinition.Rule;

import static org.junit.Assert.assertEquals;
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
    public void ConfiguresAlignRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_ALIGN);
        assertEquals(Severity.MINOR, rule.severity());

        assertEquals(3, rule.params().size());

    }

    @Test
    public void ConfiguresBanRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_BAN);
        assertEquals(Severity.CRITICAL, rule.severity());
    }

    @Test
    public void ConfiguresClassNameRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_CLASS_NAME);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresCommentFormatRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_COMMENT_FORMAT);
        assertEquals(Severity.MINOR, rule.severity());

        assertEquals(2, rule.params().size());

    }

    @Test
    public void ConfiguresCurlyRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_CURLY);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresEofLineRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_EOFLINE);
        assertEquals(Severity.MINOR, rule.severity());
    }

    @Test
    public void ConfiguresForInRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_FORIN);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresIndentRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_INDENT);
        assertEquals(Severity.MINOR, rule.severity());

    }

    @Test
    public void ConfiguresInterfaceNameRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_INTERFACE_NAME);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresJsDocFormat() {
        Rule rule = getRule(TsRulesDefinition.RULE_JSDOC_FORMAT);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresLabelPositionRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_LABEL_POSITION);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresLabelUndefinedRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_LABEL_UNDEFINED);
        assertEquals(Severity.CRITICAL, rule.severity());
    }

    @Test
    public void ConfiguresMaxLineLengthRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_MAX_LINE_LENGTH);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresMemberAccessRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_MEMBER_ACCESS);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresMemberOrderingRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_MEMBER_ORDERING);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoAnyRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_ANY);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoArgRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_ARG);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoBitwiseRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_BITWISE);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoConditionalAssignmentRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_CONDITIONAL_ASSIGNMENT);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoConsoleRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_CONSOLE);
        assertEquals(Severity.MAJOR, rule.severity());

    }

    @Test
    public void ConfiguresNoConsecutiveBlankLinesRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_CONSECUTIVE_BLANK_LINES);
        assertEquals(Severity.MINOR, rule.severity());
    }

    @Test
    public void ConfiguresNoConstructRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_CONSTRUCT);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoConstructorVarsRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_CONSTRUCTOR_VARS);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoDebuggerRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_DEBUGGER);
        assertEquals(Severity.CRITICAL, rule.severity());
    }

    @Test
    public void ConfiguresNoDuplicateKeyRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_DUPLICATE_KEY);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoSHadowedVariableRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_SHADOWED_VARIABLE);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoDuplicateVariableRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_DUPLICATE_VARIABLE);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoEmptyRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_EMPTY);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoEvalRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_EVAL);
        assertEquals(Severity.CRITICAL, rule.severity());
    }

    @Test
    public void ConfiguresNoInternalModuleRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_INTERNAL_MODULE);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoRequireImportsRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_REQUIRE_IMPORTS);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoStringLiteralRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_STRING_LITERAL);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoSwitchFallThroughRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_SWITCH_CASE_FALL_THROUGH);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoTrailingCommaRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_TRAILING_COMMA);
        assertEquals(Severity.MINOR, rule.severity());
    }

    @Test
    public void ConfiguresNoTrailingWhitespaceRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_TRAILING_WHITESPACE);
        assertEquals(Severity.MINOR, rule.severity());
    }

    @Test
    public void ConfiguresNoUnreachableRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_UNREACHABLE);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoUnusedExpressionRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_UNUSED_EXPRESSION);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoUnusedVariableRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_UNUSED_VARIABLE);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoUseBeforeDeclareRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_USE_BEFORE_DECLARE);
        assertEquals(Severity.CRITICAL, rule.severity());
    }

    @Test
    public void ConfiguresNoVarKeywordRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_VAR_KEYWORD);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresNoVarRequiresRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_NO_VAR_REQUIRES);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresOneLineRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_ONE_LINE);
        assertEquals(Severity.MINOR, rule.severity());
    }

    @Test
    public void ConfiguresQuoteMarkRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_QUOTEMARK);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresRadixRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_RADIX);
        assertEquals(Severity.CRITICAL, rule.severity());
    }

    @Test
    public void ConfiguresSemicolonRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_SEMICOLON);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresSortObjectLiteralKeysRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_SORT_OBJECT_LITERAL_KEYS);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresSwitchDefaultRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_SWITCH_DEFAULT);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresTripleEqualsRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_TRIPLE_EQUALS);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresTypedefRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_TYPEDEF);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresTypedefWhitespaceRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_TYPEDEF_WHITESPACE);
        assertEquals(Severity.MINOR, rule.severity());
    }

    @Test
    public void ConfiguresUseStrictRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_USE_STRICT);
        assertEquals(Severity.CRITICAL, rule.severity());
    }

    @Test
    public void ConfiguresVariableNameRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_VARIABLE_NAME);
        assertEquals(Severity.MAJOR, rule.severity());
    }

    @Test
    public void ConfiguresWhitespaceRule() {
        Rule rule = getRule(TsRulesDefinition.RULE_WHITESPACE);
        assertEquals(Severity.MINOR, rule.severity());
    }

    private Rule getRule(String name) {
        this.definition.define(context);
        return this.context.repository(TsRulesDefinition.REPOSITORY_NAME).rule(name);
    }

}
