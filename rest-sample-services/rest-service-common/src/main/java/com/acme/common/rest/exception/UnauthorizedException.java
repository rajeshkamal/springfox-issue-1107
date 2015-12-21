package com.acme.common.rest.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AbstractRestException {

    private static final long serialVersionUID = -6741020382231190448L;

    public UnauthorizedException(Exception e) {
        super(e, HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException(Exception e, String url) {
        super(e, HttpStatus.UNAUTHORIZED, url);
    }

}
