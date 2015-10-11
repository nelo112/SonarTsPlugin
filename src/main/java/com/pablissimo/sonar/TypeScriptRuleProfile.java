package com.pablissimo.sonar;

import com.pablissimo.sonar.rules.TsLintRule;
import com.pablissimo.sonar.rules.TsRulesDefinition;
import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.utils.ValidationMessages;

public class TypeScriptRuleProfile extends ProfileDefinition {

    @Override
    public RulesProfile createProfile(ValidationMessages validation) {
        RulesProfile profile = RulesProfile.create("TsLint", TypeScriptLanguage.LANGUAGE_EXTENSION);

        for (TsLintRule rule : TsLintRule.values()) {
            activateRule(profile, rule.getKey());
        }

        return profile;
    }

    private static void activateRule(RulesProfile profile, String ruleKey) {
        profile.activateRule(Rule.create(TsRulesDefinition.REPOSITORY_NAME, ruleKey), null);
    }
}
