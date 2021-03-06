package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.HARDWARE_COMPONENT;


@Data
@Document(HARDWARE_COMPONENT)
@ApiModel(description = "Details about hardware-component")
public class HardwareComponent implements DocumentEntity {

    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    private String name;
    private String description;

    @Field("hardware-rev")
    @JsonProperty("hardware-rev")
    private String hardwareRev;

    @Field("board-id")
    @JsonProperty("board-id")
    private String boardId;

    @Field("part-number")
    @JsonProperty("part-number")
    private String partNumber;

}
