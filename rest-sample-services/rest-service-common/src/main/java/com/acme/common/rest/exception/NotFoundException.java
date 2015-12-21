package com.acme.common.rest.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractRestException {

    private static final long serialVersionUID = -7002860283548148733L;

    public <ENTITY, ID> NotFoundException(Class<ENTITY> entityClass, ID id) {
        super(new DefaultMessageSourceResolvable(new String[]{"entity.not.found"},
                new Object[]{new DefaultMessageSourceResolvable("entity-" + entityClass.getSimpleName()), id},
                String.format("%s with id %s not found (default message)", entityClass.getSimpleName(), id)
        ), HttpStatus.NOT_FOUND);
    }

    public NotFoundException(Exception e) {
        super(e, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(Exception e, String url) {
        super(e, HttpStatus.NOT_FOUND, url);
    }

}
