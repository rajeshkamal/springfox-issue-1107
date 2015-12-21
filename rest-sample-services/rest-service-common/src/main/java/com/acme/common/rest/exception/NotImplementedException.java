package com.acme.common.rest.exception;

import org.springframework.http.HttpStatus;

public class NotImplementedException extends AbstractRestException {

    private static final long serialVersionUID = -2424589301661612469L;

    public NotImplementedException() {
        super(new Exception("NOT_IMPLEMENTED"), HttpStatus.NOT_IMPLEMENTED);
    }

}
