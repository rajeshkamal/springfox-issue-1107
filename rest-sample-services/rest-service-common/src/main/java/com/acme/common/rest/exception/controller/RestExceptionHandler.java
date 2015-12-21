package com.acme.common.rest.exception.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.NoHandlerFoundException;

import springfox.documentation.annotations.ApiIgnore;

import com.acme.common.rest.exception.AbstractRestException;
import com.acme.common.rest.exception.AccessForbiddenException;
import com.acme.common.rest.exception.BadRequestException;
import com.acme.common.rest.exception.InternalServerErrorException;
import com.acme.common.rest.exception.NotFoundException;
import com.acme.common.rest.exception.NotImplementedException;
import com.acme.common.rest.exception.UnauthorizedException;
import com.acme.common.rest.model.Error;
import com.acme.common.rest.model.ErrorBase;
import com.acme.common.rest.model.ResolvedError;

@ControllerAdvice
@ApiIgnore
public class RestExceptionHandler {

    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource, LocaleResolver localeResolver) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @ExceptionHandler({ InternalServerErrorException.class, NotFoundException.class,
            AccessForbiddenException.class, BadRequestException.class, UnauthorizedException.class,
            NotImplementedException.class })
    public ResponseEntity<ResolvedError> restException(AbstractRestException exception,
            HttpServletRequest request) {
        return makeErrorEntity(exception, request);
    }

    private <E extends AbstractRestException> ResponseEntity<ResolvedError> makeErrorEntity(
            E exception, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ErrorBase error = exception.getError();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<ResolvedError>(resolveError(error, request), headers,
                HttpStatus.valueOf(error.getStatusCode()));
    }

    /**
     * Generic exception handler for throwing an error model when the requested URL path cannot be
     * mapped by the spring dispatcher servlet.
     *
     * @param exception
     *            {@link NoHandlerFoundException}
     * @return {@link ResolvedError}
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResolvedError noHandlerFoundException(NoHandlerFoundException exception) {
        HttpStatus returnStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResolvedError(exception.getLocalizedMessage(), returnStatus.value(),
                returnStatus.getReasonPhrase(), exception.getRequestURL());
    }

    private ResolvedError resolveError(ErrorBase e, HttpServletRequest request) {
        ResolvedError error;
        if (e instanceof ResolvedError)
            error = (ResolvedError) e;
        else {
            Locale locale = localeResolver.resolveLocale(request);
            String resolvedMessage = messageSource.getMessage(((Error) e).getMessage(), locale);
            error =
                    new ResolvedError(resolvedMessage, e.getStatusCode(), e.getStatusMessage(),
                            e.getUrl());
        }
        return error;
    }
}
