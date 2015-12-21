package com.acme.common.rest.model;

import org.springframework.context.MessageSourceResolvable;

public class Error extends ErrorBase {

    public MessageSourceResolvable getMessage() {
        return message;
    }

    private final MessageSourceResolvable message;

    public Error(MessageSourceResolvable message, int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
        this.message = message;
    }

    public Error(MessageSourceResolvable message, int statusCode, String statusMessage, String url) {
        super(statusCode, statusMessage, url);
        this.message = message;
    }
}
