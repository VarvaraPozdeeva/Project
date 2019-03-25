package com.netcracker.device;

public class Network {

    private String mac;
    private String ip;

    public Network(){

    }
    public String getMac() {
        return mac;
    }

    public String getIp() {
        return ip;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Network{" +
                "mac='" + mac + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
