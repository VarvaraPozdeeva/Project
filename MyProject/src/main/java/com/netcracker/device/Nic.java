package com.netcracker.device;

public class Nic {
    private String mac;
    private String ip;
    private String gateway;

    public Nic(){ }

    public String getMac() {
        return mac;
    }

    public String getIp() {
        return ip;
    }

    public String getGateway() {
        return gateway;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public String toString() {
        return "Nic{" +
                "mac='" + mac + '\'' +
                ", ip='" + ip + '\'' +
                ", gateway='" + gateway + '\'' +
                '}';
    }
}
