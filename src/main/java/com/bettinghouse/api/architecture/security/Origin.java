package com.bettinghouse.api.architecture.security;

public enum Origin {

    BETTING_HOUSE(15);

    int refreshTime;

    Origin(int refreshTime) {
        this.refreshTime = refreshTime;
    }

    public static boolean isThere(String value) {
        Origin[] OriginsArray = Origin.values();
        for (Origin origin : OriginsArray) {
            if (origin.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int getRefreshTime() {
        return refreshTime;
    }
}
