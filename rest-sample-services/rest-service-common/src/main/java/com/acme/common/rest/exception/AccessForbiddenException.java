package com.acme.common.rest.exception;

import org.springframework.http.HttpStatus;

public class AccessForbiddenException extends AbstractRestException {

    private static final long serialVersionUID = 3085936305369954509L;

    public AccessForbiddenException(Exception e) {
        super(e, HttpStatus.FORBIDDEN);
    }

    public AccessForbiddenException(Exception e, String url) {
        super(e, HttpStatus.FORBIDDEN, url);
    }
}
