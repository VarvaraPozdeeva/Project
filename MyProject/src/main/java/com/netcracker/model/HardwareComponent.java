package com.netcracker.model;

import com.arangodb.springframework.annotation.Document;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
@Document("hwComponent")
@ApiModel(description = "Details about hardware-component")
public class HardwareComponent {

    @Id
    private String id;

    private String name;
    private String hardwareRev;
    private String operState;
    private String description;
    private String adminState;
    private String type;
    private String stateLastChanged;
    private String softwareRev;
    private String firmwareRev;
    private String serialNumber;
    private String hardwareStatus;
}
