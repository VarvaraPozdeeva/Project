package com.netcracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Details about Router. ")
public class Router {

    @ApiModelProperty(notes = "nets ")
    private Network[] nets;
}
