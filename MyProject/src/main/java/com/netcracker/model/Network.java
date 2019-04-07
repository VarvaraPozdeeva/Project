package com.netcracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Details about Device. ")
public class Network {

    @ApiModelProperty(notes = "mac ")
    private String mac;
    @ApiModelProperty(notes = "ip ")
    private  String ip;
}
