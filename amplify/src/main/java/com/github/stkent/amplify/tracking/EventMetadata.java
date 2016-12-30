package com.github.stkent.amplify.tracking;

import android.support.annotation.Nullable;

public final class EventMetadata {

    public static EventMetadata newInstance() {
        return new EventMetadata();
    }

    private final int count;

    private final Long firstTimeMillis;

    private final Long lastTimeMillis;

    private final String lastVersionName;

    private final Integer lastVersionCode;

    private EventMetadata() {
        this.count = 0;
        this.firstTimeMillis = null;
        this.lastTimeMillis = null;
        this.lastVersionName = null;
        this.lastVersionCode = null;
    }

    private EventMetadata(
            final int count,
            final Long firstTimeMillis,
            final Long lastTimeMillis,
            final String lastVersionName,
            final Integer lastVersionCode) {

        this.count = count;
        this.firstTimeMillis = firstTimeMillis;
        this.lastTimeMillis = lastTimeMillis;
        this.lastVersionName = lastVersionName;
        this.lastVersionCode = lastVersionCode;
    }

    public int getCount() {
        return count;
    }

    @Nullable
    public Long getFirstTimeMillis() {
        return firstTimeMillis;
    }

    @Nullable
    public Long getLastTimeMillis() {
        return lastTimeMillis;
    }

    @Nullable
    public String getLastVersionName() {
        return lastVersionName;
    }

    @Nullable
    public Integer getLastVersionCode() {
        return lastVersionCode;
    }

}
