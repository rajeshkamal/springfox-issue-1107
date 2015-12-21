package com.acme.common.rest.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for {@link Error} (resolvable error) and {@link ResolvedError}.
 */
@Getter
@Setter
public abstract class ErrorBase {
    public ErrorBase(int statusCode, String statusMessage) {
        this(statusCode, statusMessage, null);
    }

    public ErrorBase(int statusCode, String statusMessage, String url) {
        this.url = url;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    @ApiModelProperty(readOnly = true, value = "HTTP URL which caused the error")
    protected String url;
    @ApiModelProperty(readOnly = true, value = "HTTP status code", allowableValues = "200,201,202,204,400,401,403,404,415,500")
    protected int statusCode;
    @ApiModelProperty(readOnly = true, value = "HTTP status message")
    protected String statusMessage;

}
