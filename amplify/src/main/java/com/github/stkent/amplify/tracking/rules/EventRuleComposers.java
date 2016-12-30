package com.github.stkent.amplify.tracking.rules;

import android.support.annotation.NonNull;

import com.github.stkent.amplify.tracking.interfaces.IEventRule;

import java.util.Arrays;

public final class EventRuleComposers {

    @NonNull
    public static IEventRule anyOf(@NonNull final IEventRule... eventRules) {
        return new IEventRule() {
            @Override
            public boolean shouldAllowFeedbackPrompt(@NonNull final Object environment) {
                for (final IEventRule eventRule : eventRules) {
                    if (eventRule.shouldAllowFeedbackPrompt(environment)) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public String toString() {
                return "AnyOf" + Arrays.toString(eventRules);
            }
        };
    }

    @NonNull
    public static IEventRule allOf(@NonNull final IEventRule... eventRules) {
        return new IEventRule() {
            @Override
            public boolean shouldAllowFeedbackPrompt(@NonNull final Object environment) {
                for (final IEventRule eventRule : eventRules) {
                    if (!eventRule.shouldAllowFeedbackPrompt(environment)) {
                        return false;
                    }
                }

                return true;
            }

            @Override
            public String toString() {
                return "AllOf" + Arrays.toString(eventRules);
            }
        };
    }

    private EventRuleComposers() {
        // This constructor intentionally left blank.
    }

}
