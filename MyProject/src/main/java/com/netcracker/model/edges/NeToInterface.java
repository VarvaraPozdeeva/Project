package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.NetworkElement;
import com.netcracker.model.documents.Interface;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.NE_TO_INTERFACE;

@Data
@Edge(NE_TO_INTERFACE)

@ApiModel(description = "Details about ne-to-interface")
public class NeToInterface  {

    @Id
    private String id;
    @From
    private final NetworkElement ne;
    @To
    private final Interface anInterface;

}
