package com.netcracker.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Nic {

    String mac;
    String ip;
    String gateway;
}
