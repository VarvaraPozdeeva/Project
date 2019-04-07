package com.netcracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@ApiModel(description = "Details about Nic. ")
public class Nic {

    @ApiModelProperty(notes = "mac ")
    private String mac;
    @ApiModelProperty(notes = "ip ")
    private String ip;
    @ApiModelProperty(notes = "gateway ")
    private String gateway;
}
