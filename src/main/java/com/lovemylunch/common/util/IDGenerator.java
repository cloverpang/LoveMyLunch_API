package com.lovemylunch.common.util;

import java.util.UUID;

public class IDGenerator {
    public static final int UUID_MAX_LEN = 36;

    private IDGenerator() {
    }

    public static final String uuid() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        if(uuid.length() > 36) {
            uuid = uuid.substring(0, 36);
        }

        return uuid;
    }
}
