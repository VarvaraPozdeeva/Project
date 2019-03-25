package com.netcracker.controller;

import com.netcracker.device.Devices;
import com.netcracker.device.parsetoPOJOfromJSON.Parse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("device")
public class Controller {
    Devices d = new Devices();
    @RequestMapping( method = RequestMethod.GET )
   public Devices getDevices(){
        try {
            d = Parse.parsFromJson();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }
}
