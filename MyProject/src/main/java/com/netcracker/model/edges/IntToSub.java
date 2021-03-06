package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.SubInterface;
import com.netcracker.model.documents.Interface;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.INT_TO_SUB;

@Data
@Edge(INT_TO_SUB)
@ApiModel(description = "Details about int-to-sub")
public class IntToSub implements EdgeEntity {
    @Id
    private String id;

    @From
    private final Interface anInterface;

    @To
    private final SubInterface subInterface;

}
