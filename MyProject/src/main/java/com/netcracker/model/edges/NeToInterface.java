package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.NetworkElement;
import com.netcracker.model.documents.Interface;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Edge("ne-to-interface")
public class NeToInterface {

    @Id
    private String id;
    @From
    private final NetworkElement ne;
    @To
    private final Interface anInterface;

}
