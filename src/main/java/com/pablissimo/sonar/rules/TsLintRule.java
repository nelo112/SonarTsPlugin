package com.pablissimo.sonar.rules;

import org.sonar.api.rule.Severity;

import java.util.HashSet;
import java.util.Set;

/**
 * Enum of known tslint rules.
 */
public enum TsLintRule {

    ALIGN("align", "Enforces vertical Alignment", Severity.MINOR),
    BAN("ban", "Use of this method is banned by current configuration", Severity.CRITICAL),
    CLASS_NAME("class-name", "Name must use PascalCase", Severity.MAJOR),
    COMMENT_FORMAT("comment-format","Comments must be correctly formatted", Severity.MINOR),
    CURLY("curly", "enforces braces for if/for/do/while statements", Severity.MAJOR),
    EOFLINE("eofline", "enforces the file to end with a newline", Severity.MINOR),
    FORIN("forin", "enforces a for ... in statement to be filtered with an if statement", Severity.MAJOR),
    INDENT("indent", "enforces consistent indentation with tabs or spaces", Severity.MINOR),
    INTERFACE_NAME("interface-name", "enforces the rule that interface names must begin with a capital I", Severity.MAJOR),
    JSDOC_FORMAT("jsdoc-format", "enforces basic format rules for jsdoc comments - comments starting with /**", Severity.MAJOR),
    LABEL_POSITION("label-position", "enforces labels only on sensible statements", Severity.MAJOR),
    LABEL_UNDEFINED("label-undefined", "checks that labels are defined before usage", Severity.CRITICAL),
    MAX_LINE_LENGTH("max-line-length", "sets the maximum length of a line", Severity.MAJOR),
    MEMBER_ACCESS("member-access", "Enforces using explicit visibility on class members", Severity.MAJOR),
    MEMBER_ORDERING("member-ordering", "enforces ordering of class members", Severity.MAJOR),
    NO_ANY("no-any", "'any' must not be used as a type decoration", Severity.MAJOR),
    NO_ARG("no-arg", "arguments.callee must not be used", Severity.MAJOR),
    NO_BITWISE("no-bitwise", "bitwise operators must not be used", Severity.MAJOR),
    NO_CONDITIONAL_ASSIGNMENT("no-conditional-assignment", "Disallows any type of assignment in any conditionals. this applies to do-while, for, if, and while statements", Severity.MAJOR),
    NO_CONSOLE("no-console", "Specified function must not be called on the global console object", Severity.MAJOR),
    NO_CONSECUTIVE_BLANK_LINES("no-consecutive-blank-lines", "No more than one blank line should appear in a row", Severity.MINOR),
    NO_CONSTRUCT("no-construct", "Constructors of String, Number and Boolean must not be used", Severity.MAJOR),
    NO_CONSTRUCTOR_VARS("no-constructor-vars", "Public and private modifiers must not be used on constructor arguments", Severity.MAJOR),
    NO_DEBUGGER("no-debugger", "Debugger statements are not allowed", Severity.CRITICAL),
    NO_DUPLICATE_KEY("no-duplicate-key", "Duplicate keys must not be specified in object literals", Severity.MAJOR),
    NO_SHADOWED_VARIABLE("no-shadowed-variable", "Disallows shadowed variable declarations", Severity.MAJOR),
    NO_DUPLICATE_VARIABLE("no-duplicate-variable", "Duplicate variable definitions are not allowed", Severity.MAJOR),
    NO_EMPTY("no-empty", "Empty blocks are not allowed", Severity.MAJOR),
    NO_EVAL("no-eval", "Use of eval is not allowed", Severity.CRITICAL),
    NO_INTERNAL_MODULE("no-internal-module", "Disallows internal module, use namespace instead", Severity.MAJOR),
    NO_REQUIRE_IMPORTS("no-require-imports", "Disallows require() style imports", Severity.MAJOR),
    NO_STRING_LITERAL("no-string-literal", "Object access via string literals is not allowed", Severity.MAJOR),
    NO_SWITCH_CASE_FALL_THROUGH("no-switch-case-fall-through", "Falling through one case statement to another is not allowed", Severity.MAJOR),
    NO_TRAILING_COMMA("no-trailing-comma", "Trailing commas should not be used within object literals", Severity.MINOR),
    NO_TRAILING_WHITESPACE("no-trailing-whitespace", "Trailing whitespace at the end of lines is not allowed", Severity.MINOR),
    NO_UNREACHABLE("no-unreachable", "Unreachable code after break, catch, throw and return statements is not allowed", Severity.MAJOR),
    NO_UNUSED_EXPRESSION("no-unused-expression", "Unused expressions (those that aren't assignments or function calls) are not allowed", Severity.MAJOR),
    NO_UNUSED_VARIABLE("no-unused-variable", "Unused imports, variables, functions and private class members are not allowed", Severity.MAJOR),
    NO_USE_BEFORE_DECLARE("no-use-before-declare", "Variable use before declaration is not allowed", Severity.CRITICAL),
    NO_VAR_KEYWORD("no-var-keyword", "Disallows usage of the var keyword, use let or const instead", Severity.MAJOR),
    NO_VAR_REQUIRES("no-var-requires", "Require is only allowed in import statements", Severity.MAJOR),
    ONE_LINE("one-line", "No newline is allowed before keyword", Severity.MINOR),
    QUOTEMARK("quotemark", "Consistent use of single or double quotes is required - a mixture is not allowed", Severity.MAJOR),
    RADIX("radix", "A radix must be specified when calling parseInt", Severity.CRITICAL),
    SEMICOLON("semicolon", "Statement must end with a semicolon", Severity.MAJOR),
    SORT_OBJECT_LITERAL_KEYS("sort-object-literal-keys", "Checks that keys in object literals are declared in alphabetical order", Severity.MAJOR),
    SWITCH_DEFAULT("switch-default", "Enforces a default case in switch statements", Severity.MAJOR),
    TRIPLE_EQUALS("triple-equals", "== and != must not be used - use === or !== instead", Severity.MAJOR),
    TYPEDEF("typedef", "Type definition must be specified", Severity.MAJOR),
    TYPEDEF_WHITESPACE("typedef-whitespace", "Whitespace around type definitions must be correct", Severity.MINOR),
    USE_STRICT("use-strict", "Strict mode must be used", Severity.CRITICAL),
    VARIABLE_NAME("variable-name", "Variable names must be either camelCased or UPPER_CASED", Severity.MAJOR),
    WHITESPACE("whitespace", "Inappropriate whitespace between tokens", Severity.MINOR),

    UNKNOWN_RULE("UNKNOWN", "Unknown tslint issue", Severity.MAJOR);

    /**
     * The rule key, used internally and in tslint.json.
     */
    private final String key;

    /**
     * The rule title, for display in SonarQube.
     */
    private final String title;

    /**
     * The default severity.
     */
    private final String defaultSeverity;

    TsLintRule(String key, String title, String defaultSeverity) {
        this.key = key;
        this.title = title;
        this.defaultSeverity = defaultSeverity;
    }

    /**
     * @return the rule key.
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the rule title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the default severity.
     */
    public String getDefaultSeverity() {
        return defaultSeverity;
    }

    /**
     * @return all rule keys.
     */
    public static Set<String> allKeys() {
        Set<String> keys = new HashSet<>();
        for (TsLintRule rule : values()) {
            keys.add(rule.getKey());
        }
        return keys;
    }

}
