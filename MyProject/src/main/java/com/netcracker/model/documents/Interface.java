package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.model.edges.IntToSub;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

import static com.netcracker.model.CollectionsNames.INTERFACE;

@Data
@Document(INTERFACE)
@ApiModel(description = "Details about interface. ")
public class Interface implements DocumentEntity {

    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    private String name;
    private String description;

    @Field("mac-address")
    @JsonProperty("mac-address")
    private String macAddress;

    @Field("ip-address")
    @JsonProperty("ip-address")
    private String ipAddress;

    private int mtu;

    private String speed;

    private String duplex;

    @Field("admin-status")
    @JsonProperty("admin-status")
    private String adminStatus;

    @Field("port-mode")
    @JsonProperty("port-mode")
    private String portMode;

    private Set<String> vlans;

    @Relations(edges = IntToSub.class, direction = Relations.Direction.OUTBOUND)
    @ApiModelProperty(hidden = true)
    @JsonProperty("sub-interface")
    private List<SubInterface> subInterfaces;
}
