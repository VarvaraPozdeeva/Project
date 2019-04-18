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

@Data
@Edge("link")
@ApiModel(description = "Details about link")
public class Link {

    @Id
    private String id;

    @From
    private final Interface anInterface;
    @To
    private final Interface bInterface;
    @Field("a-ne")
    @JsonProperty("a-ne")
    private String aNeName;
    @Field("z-ne")
    @JsonProperty("z-ne")
    private String zNeName;
    @Field("a-interface")
    @JsonProperty("a-interface")
    private String aInterName;
    @Field("z-interface")
    @JsonProperty("z-interface")
    private String zInterName;

}
