package com.netcracker.device;

import java.util.Arrays;

public class Router {
    private Network[] nets;

    public Router(){

    }
    public Network[] getNets() {
        return nets;
    }

    public void setNets(Network[] nets) {
        this.nets = nets;
    }
    @Override
    public String toString() {
        return "Router{" +
                "nets=" + Arrays.toString(nets) +
                '}';
    }
}
