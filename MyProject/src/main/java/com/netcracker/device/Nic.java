package com.netcracker.device;

import lombok.Data;

@Data
public class Nic {
    private String mac;
    private String ip;
    private String gateway;
}
