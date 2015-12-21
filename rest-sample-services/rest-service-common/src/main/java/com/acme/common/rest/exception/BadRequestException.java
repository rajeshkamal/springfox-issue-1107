package com.acme.common.rest.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AbstractRestException {

    private static final long serialVersionUID = -8998621186346327200L;

    public BadRequestException(Exception e) {
        super(e, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(Exception e, String url) {
        super(e, HttpStatus.BAD_REQUEST, url);
    }
}