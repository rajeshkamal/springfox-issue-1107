package com.acme.vdi.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Task Model.
 */
@Getter
@Setter
@ToString
@ApiModel(description = "Task model")
public class Task {

    @ApiModelProperty(readOnly = true, value = "Task unique identifier")
    private String id;

    @ApiModelProperty(value = "Task name", readOnly = true)
    private String name;

    @ApiModelProperty(value = "Task status", readOnly = true)
    private String status;

    @ApiModelProperty(value = "Task status", readOnly = true)
    private int progress;

}
