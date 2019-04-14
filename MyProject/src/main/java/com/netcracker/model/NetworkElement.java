package com.netcracker.model;

import com.arangodb.springframework.annotation.Document;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Document("netElement")
@ApiModel(description = "Details about Network-element")
public class NetworkElement {

    @Id
    private String id;
    private Date createdDate;
    private String hardwareModel;
    private String hostName;
    private String ipAddress;
    private String osVersion;
    private String type;
    private String vendor;
    private Date sysBootedTime;
    private Date lastSync;

}