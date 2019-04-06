package com.netcracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Details about Switch. ")
public class Switch {

    @ApiModelProperty(notes = "ports ")
    String[] ports;
}
