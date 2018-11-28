package com.lovemylunch.common.exceptions;

public class AwfException extends Exception {
    private static final long serialVersionUID = -469550168296181177L;

    public AwfException(String message) {
        super(message);
    }

    public AwfException(String message, Throwable cause) {
        super(message, cause);
    }
}