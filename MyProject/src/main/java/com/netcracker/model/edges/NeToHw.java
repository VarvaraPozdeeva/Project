package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.NetworkElement;
import com.netcracker.model.documents.HardwareComponent;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Edge("ne-to-hw")
@ApiModel(description = "Details about ne-to-hw")
public class NeToHw {

    @Id
    private String id;

    @From
    private NetworkElement ne;
    @To
    private HardwareComponent hw;
}