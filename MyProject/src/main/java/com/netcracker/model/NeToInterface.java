package com.netcracker.model;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Edge("ne-to-interface")
public class NeToInterface {

    @Id
    private String id;
    @From
    private NetworkElement ne;
    @To
    private Interface anInterface;
}
