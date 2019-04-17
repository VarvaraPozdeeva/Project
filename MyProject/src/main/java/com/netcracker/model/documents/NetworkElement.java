package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.model.edges.NeToHw;
import com.netcracker.model.edges.NeToInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@Document("netElement")
@ApiModel(description = "Details about Network-element")
public class NetworkElement {

    @Id
    private String id;

    private String name;
    private String type;
    private String vendor;

    @Field("ip-address")
    @JsonProperty("ip-address")
    private String ipAddress;

    @Field("os-version")
    @JsonProperty("os-version")
    private String osVersion;

    @Field("returned-to-rom")
    @JsonProperty("returned-to-rom")
    private String returnedToRom;

    @Field("system-booted-time")
    @JsonProperty("system-booted-time")
    private Date systemBootedTime;

    @Field("last-sync")
    @JsonProperty("last-sync")
    private Date lastSync;

    @Relations(edges = NeToHw.class, direction = Relations.Direction.OUTBOUND)
    @ApiModelProperty(hidden = true)
    @JsonProperty("hw-component")
    private HardwareComponent hardwareComponent;

    @Relations(edges = NeToInterface.class, direction = Relations.Direction.OUTBOUND)
    @ApiModelProperty(hidden = true)
    @JsonProperty("interfaces")
    private List<Interface> interfaces;


}