package com.netcracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Details about Device. ")
public class Network {

    @ApiModelProperty(notes = "mac ")
    String mac;
    @ApiModelProperty(notes = "ip ")
    String ip;
}
