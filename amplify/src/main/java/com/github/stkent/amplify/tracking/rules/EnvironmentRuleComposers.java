package com.github.stkent.amplify.tracking.rules;

import android.support.annotation.NonNull;

import com.github.stkent.amplify.IEnvironment;
import com.github.stkent.amplify.tracking.interfaces.IEnvironmentRule;

import java.util.Arrays;

public final class EnvironmentRuleComposers {

    @NonNull
    public static IEnvironmentRule anyOf(@NonNull final IEnvironmentRule... environmentRules) {
        return new IEnvironmentRule() {
            @Override
            public boolean shouldAllowFeedbackPrompt(@NonNull final IEnvironment environment) {
                for (final IEnvironmentRule environmentRule : environmentRules) {
                    if (environmentRule.shouldAllowFeedbackPrompt(environment)) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public String toString() {
                return "AnyOf" + Arrays.toString(environmentRules);
            }
        };
    }

    @NonNull
    public static IEnvironmentRule allOf(@NonNull final IEnvironmentRule... environmentRules) {
        return new IEnvironmentRule() {
            @Override
            public boolean shouldAllowFeedbackPrompt(@NonNull final IEnvironment environment) {
                for (final IEnvironmentRule environmentRule : environmentRules) {
                    if (!environmentRule.shouldAllowFeedbackPrompt(environment)) {
                        return false;
                    }
                }

                return true;
            }

            @Override
            public String toString() {
                return "AllOf" + Arrays.toString(environmentRules);
            }
        };
    }

    private EnvironmentRuleComposers() {
        // This constructor intentionally left blank.
    }

}
