package com.netcracker.model;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Edge("hw-to-hw")
public class HwToHw {

    @Id
    private String id;

    @From
    private HardwareComponent hwFrom;
    @To
    private HardwareComponent hwTo;
}