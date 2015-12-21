package com.acme.common.rest.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends AbstractRestException {

    private static final long serialVersionUID = -7971242988504131720L;

    public InternalServerErrorException(Exception e) {
        super(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(Exception e, String url) {
        super(e, HttpStatus.INTERNAL_SERVER_ERROR, url);
    }
}