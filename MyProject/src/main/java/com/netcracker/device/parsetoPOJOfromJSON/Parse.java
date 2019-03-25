package com.netcracker.device.parsetoPOJOfromJSON;

import com.netcracker.device.Devices;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.*;

public class Parse {
    public static void main(String[] args) throws  IOException {
        Devices dev = parsFromJson();
        System.out.println(dev);
    }
    public static Devices parsFromJson() throws  IOException {
        InputStream file = new FileInputStream(".\\src\\main\\resources\\dev.json");
        ObjectMapper mapper = new ObjectMapper();
        Devices dev = mapper.readValue(file, Devices.class);
        return dev;
    }
}
