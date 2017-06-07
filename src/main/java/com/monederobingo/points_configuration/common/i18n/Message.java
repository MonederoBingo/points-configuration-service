package com.monederobingo.points_configuration.common.i18n;

public enum Message {
    SERVER_ERROR("server.error"),
    UPDATED_CONFIGURATION("updated.configuration");

    private final String message;

    Message(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
