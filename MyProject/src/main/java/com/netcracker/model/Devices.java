package com.netcracker.model;

import com.arangodb.springframework.annotation.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@Document("devices")
@ApiModel(description = "Details about Device. ")
public class Devices {

    @Id
    @JsonIgnore
    @ApiModelProperty(notes = "database generated  id")
    private String id;

    @ApiModelProperty(notes = "nics ")
    @NonNull
    private Nic[] nics;

    @ApiModelProperty(notes = "Switch ")
    @NonNull
    private Switch[] switches;

    @ApiModelProperty(notes = "Router ")
    @NonNull
    private Router[] routers;
}
