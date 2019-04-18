package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.model.documents.Interface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@ApiModel(description = "Details about link")
@Edge("link")
@Data
public class Link {

    @Id
    @ApiModelProperty(hidden = true)
    private String id;
    @From

    @ApiModelProperty(hidden = true)
    private final Interface interfaceA;
    @To
    @ApiModelProperty(hidden = true)
    private final Interface interfaceZ;

    @ApiModelProperty(hidden = true)
    @Field("a-ne")
    @JsonProperty("a-ne")
    private String neAName;

    @ApiModelProperty(hidden = true)
    @Field("z-ne")
    @JsonProperty("z-ne")
    private String neZName;

    @Field("a-interface")
    @JsonProperty("a-interface")
    private String interAId;

    @Field("interface-z")
    @JsonProperty("interface-z")
    private String interZId;

}
