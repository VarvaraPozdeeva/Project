package com.netcracker.parsetoPOJOfromJSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.netcracker.model.Devices;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Parse {
    public static List<Devices> parsFromJson() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        List<Devices> devicesList = mapper.readValue(new File(".\\src\\main\\resources\\dev.json"),
                typeFactory.constructCollectionType(List.class, Devices.class));

        return devicesList;
    }
}
