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
    @ApiModelProperty(notes = "database generated  id")
    private String id;

    @ApiModelProperty(notes = "Nics ")
    @NonNull
    private Nic[] nics;

    @ApiModelProperty(notes = "Switches ")
    @NonNull
    private Switch[] switches;

    @ApiModelProperty(notes = "Routers ")
    @NonNull
    private Router[] routers;
}
