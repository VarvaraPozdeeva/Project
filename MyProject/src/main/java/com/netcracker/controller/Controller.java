package com.netcracker.controller;

import com.netcracker.model.Devices;
import com.netcracker.parsetoPOJOfromJSON.Parse;
import com.netcracker.repository.DevRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/model")
public class Controller {

    Devices d = new Devices();
    private List<Devices> devices = null;
    @Autowired
    private DevRepository repository;

    @RequestMapping( method = RequestMethod.GET )
    public Devices getDevices(){
        try {
            devices = Parse.parsFromJson();

        } catch (IOException e) {
            e.printStackTrace();
        }
        repository.saveAll(devices);
        d = repository.findById(devices.get(0).getId()).get();
        System.out.println(d);
        return d;
    }
}
