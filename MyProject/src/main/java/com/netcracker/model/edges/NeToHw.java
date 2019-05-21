package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.NetworkElement;
import com.netcracker.model.documents.HardwareComponent;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.NE_TO_HW;

@Data
@Edge(NE_TO_HW)
@ApiModel(description = "Details about ne-to-hw")
public class NeToHw implements EdgeEntity{

    @Id
    private String id;

    @From
    private final NetworkElement ne;
    @To
    private final HardwareComponent hw;


}