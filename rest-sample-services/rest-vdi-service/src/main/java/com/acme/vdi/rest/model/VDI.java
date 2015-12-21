package com.acme.vdi.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.hateoas.ResourceSupport;

import com.google.gson.annotations.Since;
import com.acme.common.rest.version.ApiVersion;

/**
 * VDI Model.
 */
@Getter
@Setter
@ToString
@ApiModel(description = "VDI model")
public class VDI extends ResourceSupport {

    @ApiModelProperty(readOnly = true, value = "VDI unique identifier", required = true)
    private String vdiId;

    @ApiModelProperty(value = "VDI name", required = true)
    private String vdiName;

    @ApiModelProperty(readOnly = true, value = "VDI CPU capacity in GHZ")
    private Integer cpu;

    @ApiModelProperty(readOnly = true, value = "VDI Memory in MB")
    private Integer memory;

    @ApiModelProperty(readOnly = true, value = "VDI Storge in TB")
    private Integer storage;

    @ApiModelProperty(value = "VDI new2.0 property")
    @Since(ApiVersion.VERSION_2_0)
    private String new20Property;

    @ApiModelProperty(value = "VDI's deprecated property")
    @Deprecated
    private String deprecatedProperty;

}
