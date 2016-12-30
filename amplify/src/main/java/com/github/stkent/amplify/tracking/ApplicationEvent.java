package com.github.stkent.amplify.tracking;

import android.support.annotation.NonNull;

import com.github.stkent.amplify.tracking.interfaces.IEvent;

import static com.github.stkent.amplify.utils.Constants.EXHAUSTIVE_SWITCH_EXCEPTION_MESSAGE;

public enum ApplicationEvent implements IEvent {

    CRASHED,
    INSTALLED,
    UPDATED;

    @NonNull
    @Override
    public String getTrackingKey() {
        switch (this) {
            case CRASHED:
                return "CRASHED";
            case INSTALLED:
                return "INSTALLED";
            case UPDATED:
                return "UPDATED";
        }

        throw new IllegalStateException(EXHAUSTIVE_SWITCH_EXCEPTION_MESSAGE);
    }

}
