package com.netcracker.device;
import java.util.Arrays;
public class Devices {
    private Nic[] nics;
    private Switch[] switches;
    private Router[] routers;

    public Devices(){ }

    public void setNics(Nic[] nics) {
        this.nics = nics;
    }

    public void setSwitches(Switch[] switches) {
        this.switches = switches;
    }

    public void setRouters(Router[] routers) {
        this.routers = routers;
    }

    public Nic[] getNics() {
        return nics;
    }

    public Switch[] getSwitches() {
        return switches;
    }

    public Router[] getRouters() {
        return routers;
    }

    @Override
    public String toString() {
        return "Devices{ \n" +
                "nics=" + Arrays.toString(nics) +
                ",\nswitches=" + Arrays.toString(switches) +
                ",\nrouters=" + Arrays.toString(routers) +
                '}';
    }
}
