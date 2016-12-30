package com.github.stkent.amplify.tracking.interfaces;

import android.support.annotation.NonNull;

public interface IRule<T> {

    boolean shouldAllowFeedbackPrompt(@NonNull T dataType);

}
