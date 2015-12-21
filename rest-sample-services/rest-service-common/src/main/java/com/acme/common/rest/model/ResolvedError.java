package com.acme.common.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResolvedError extends ErrorBase {

    public ResolvedError(String message, int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
        this.message = message;
    }

    public ResolvedError(String message, int statusCode, String statusMessage, String url) {
        super(statusCode, statusMessage, url);
        this.message = message;
    }

    private String message;

}
