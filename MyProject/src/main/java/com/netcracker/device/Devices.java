package com.netcracker.device;

import lombok.Data;

@Data
public class Devices {
    private Nic[] nics;
    private Switch[] switches;
    private Router[] routers;
}
