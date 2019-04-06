package com.netcracker.model;

import com.arangodb.springframework.annotation.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor
@Document("devices")
@Data
@ApiModel(description = "Details about Device. ")
public class Devices {

    @Id
    @JsonIgnore
    @ApiModelProperty(notes = "database generated  id")
    String id;

    @ApiModelProperty(notes = "nics ")
    @NonNull
    Nic[] nics;

    @ApiModelProperty(notes = "Switch ")
    @NonNull
    Switch[] switches;

    @ApiModelProperty(notes = "Router ")
    @NonNull
    Router[] routers;
}
