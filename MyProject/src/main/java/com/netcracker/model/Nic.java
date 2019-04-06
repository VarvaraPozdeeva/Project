package com.netcracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Details about Nic. ")
public class Nic {

    @ApiModelProperty(notes = "mac ")
    String mac;
    @ApiModelProperty(notes = "ip ")
    String ip;
    @ApiModelProperty(notes = "gateway ")
    String gateway;
}
