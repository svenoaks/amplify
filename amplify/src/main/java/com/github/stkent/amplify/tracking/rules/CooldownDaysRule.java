/**
 * Copyright 2015 Stuart Kent
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.github.stkent.amplify.tracking.rules;

import android.support.annotation.NonNull;

import com.github.stkent.amplify.IApp;
import com.github.stkent.amplify.tracking.EventMetadata;
import com.github.stkent.amplify.tracking.interfaces.IEventRule;

import static java.util.concurrent.TimeUnit.DAYS;

public final class CooldownDaysRule implements IEventRule {

    private final long days;

    public CooldownDaysRule(final long days) {
        if (days <= 0) {
            throw new IllegalStateException("Cooldown days rule must be configured with a positive cooldown period");
        }

        this.days = days;
    }

    @Override
    public boolean shouldAllowFeedbackPrompt(
            @NonNull final EventMetadata cachedEventMetadata,
            @NonNull final IApp app,
            final long currentTimeMillis) {

        final Long firstEventTimeMillis = cachedEventMetadata.getFirstTimeMillis();

        //noinspection SimplifiableIfStatement
        if (firstEventTimeMillis == null) {
            return true;
        } else {
            return currentTimeMillis - firstEventTimeMillis >= DAYS.toMillis(days);
        }
    }

    @Override
    public String toString() {
        return "CooldownDaysRule with a cooldown period of " + days + " day" + (days > 1 ? "s" : "");
    }

}
