package com.netcracker.model;

import com.arangodb.springframework.annotation.Document;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Document("interface")
@ApiModel(description = "Details about interface. ")
public class Interface {

    @Id
    private String id;
    private String name;
    private String description;
    private String macAddress;
    private String ipAddress;
    private int[] vlans;
    private String portMode;
    private String mtu;
    private String operStatus;
    private String speed;
}
