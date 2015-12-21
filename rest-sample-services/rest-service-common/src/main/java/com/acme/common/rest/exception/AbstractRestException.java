package com.acme.common.rest.exception;

import java.util.Arrays;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;

import com.acme.common.rest.model.Error;
import com.acme.common.rest.model.ErrorBase;
import com.acme.common.rest.model.ResolvedError;

public abstract class AbstractRestException extends RuntimeException {

    private static final long serialVersionUID = 8602621375560058395L;
    private ErrorBase error;

    public AbstractRestException(MessageSourceResolvable resolvableMessage, HttpStatus statusCode) {
        this.error =
                new Error(resolvableMessage, statusCode.value(), statusCode.getReasonPhrase(), null);
    }

    public AbstractRestException(Exception e, HttpStatus statusCode) {
        super(e.getLocalizedMessage());
        this.error =
                new ResolvedError(e.getLocalizedMessage(), statusCode.value(),
                        statusCode.getReasonPhrase());
    }

    public AbstractRestException(Exception e, HttpStatus statusCode, String url) {
        super(e.getLocalizedMessage());
        this.error =
                new ResolvedError(e.getLocalizedMessage(), statusCode.value(), url,
                        Arrays.toString(e.getStackTrace()));

    }

    public ErrorBase getError() {
        return this.error;
    }

}