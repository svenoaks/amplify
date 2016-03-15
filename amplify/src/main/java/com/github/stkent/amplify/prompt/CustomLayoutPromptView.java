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
package com.github.stkent.amplify.prompt;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.github.stkent.amplify.R;
import com.github.stkent.amplify.prompt.interfaces.IPromptView;

public final class CustomLayoutPromptView
        extends BasePromptView<CustomLayoutQuestionView, CustomLayoutThanksView>
        implements IPromptView {

    private CustomLayoutPromptViewConfig config;

    public CustomLayoutPromptView(final Context context) {
        this(context, null);
    }

    public CustomLayoutPromptView(
            final Context context,
            @Nullable final AttributeSet attributeSet) {

        this(context, attributeSet, 0);
    }

    public CustomLayoutPromptView(
            final Context context,
            @Nullable final AttributeSet attributeSet,
            final int defStyleAttr) {

        super(context, attributeSet, defStyleAttr);
        init(attributeSet);
    }

    public void applyConfig(@NonNull final CustomLayoutPromptViewConfig config) {
        if (isDisplayed()) {
            throw new IllegalStateException(
                    "Configuration cannot be changed after the prompt is first displayed.");
        }

        this.config = config;
    }

    @NonNull
    @Override
    protected CustomLayoutQuestionView getQuestionView() {
        return new CustomLayoutQuestionView(getContext(), config.getQuestionLayout());
    }

    @Nullable
    @Override
    protected CustomLayoutThanksView getThanksView() {
        if (config.getThanksLayout() != null) {
            return new CustomLayoutThanksView(getContext(), config.getThanksLayout());
        }

        return null;
    }

    /**
     * Note: <code>Theme.obtainStyledAttributes</code> accepts a null <code>AttributeSet</code>; see
     * documentation of that method for confirmation.
     */
    private void init(@Nullable final AttributeSet attributeSet) {
        final TypedArray typedArray = getContext().getTheme()
                .obtainStyledAttributes(attributeSet, R.styleable.CustomLayoutPromptView, 0, 0);

        // todo: does obtainStyledAttributes ever return null? if not, can update this constructor.
        config = new CustomLayoutPromptViewConfig(typedArray);

        typedArray.recycle();
    }

}