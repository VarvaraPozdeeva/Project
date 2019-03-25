package com.netcracker.device;

import java.util.Arrays;

public class Switch {
    private String[] ports;

    public Switch(){

    }
    public String[] getPorts() {
        return ports;
    }

    public void setPorts(String[] ports) {
        this.ports = ports;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "ports=" + Arrays.toString(ports) +
                '}';
    }
}
