package com.netcracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Details about Switch. ")
public class Switch {

    @ApiModelProperty(notes = "ports ")
    private String[] ports;
}
