package com.pablissimo.sonar;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pablissimo.sonar.rules.TsLintRule;
import com.pablissimo.sonar.rules.TsRulesDefinition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.utils.ValidationMessages;

public class TypeScriptRuleProfileTest {
    ValidationMessages validationMessages;
    TypeScriptRuleProfile ruleProfile;

    @Before
    public void setUp() throws Exception {
        this.validationMessages = ValidationMessages.create();
        this.ruleProfile = new TypeScriptRuleProfile();
    }

    @Test
    public void definesExpectedRules() {
        RulesProfile profile = this.ruleProfile.createProfile(this.validationMessages);

        for (TsLintRule rule : TsLintRule.values()) {
            assertNotNull(profile.getActiveRule(TsRulesDefinition.REPOSITORY_NAME, rule.getKey()));
        }
    }
}
