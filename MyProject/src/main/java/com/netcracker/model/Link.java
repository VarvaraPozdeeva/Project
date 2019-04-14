package com.netcracker.model;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
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
    private Interface anInterface;
    @To
    private Interface bInterface;

    private String aNe;
    private String zNe;
    private String aInter;
    private String zInter;

}
