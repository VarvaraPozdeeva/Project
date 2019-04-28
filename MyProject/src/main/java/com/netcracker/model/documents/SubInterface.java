package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.SUB_INTERFACE;

@Data
@Document(SUB_INTERFACE)
@ApiModel(description = "Details about sub-interface. ")
public class SubInterface  implements  DocumentEntity{
    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    private String name;

    private String description;

    private String encapsulation;

    private String vlan;

    @Field("ip-address")
    @JsonProperty("ip-address")
    private String ipAddress;

    @Field("subnet-mask")
    @JsonProperty("subnet-mask")
    private String subNetMask;
}
