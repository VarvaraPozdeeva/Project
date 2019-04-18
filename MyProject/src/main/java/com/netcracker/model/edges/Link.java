package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.model.documents.Interface;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

@ApiModel(description = "Details about link")
@Edge("link")
@Data
public class Link {

    @Id
    private String id;
    @From
    private final Interface interfaceA;
    @To
    private final Interface interfaceZ;

    @Field("a-ne")
    @JsonProperty("a-ne")
    private String neAName;

    @Field("z-ne")
    @JsonProperty("z-ne")
    private String neZName;

    @Field("a-interface")
    @JsonProperty("a-interface")
    private String interfaceAName;

    @Field("interface-z")
    @JsonProperty("interface-z")
    private String interfaceZName;

}
