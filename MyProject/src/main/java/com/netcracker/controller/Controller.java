package com.netcracker.controller;

import com.google.common.collect.Lists;
import com.netcracker.model.Devices;
import com.netcracker.repository.DevRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/model")
@Api(value = "Network system")
public class Controller {

    private List<Devices> devices = null;
    @Autowired
    private DevRepository repository;


    @RequestMapping( method = RequestMethod.GET )
    @ApiOperation(value = "Show all devices ")
    public List<Devices> getDevices(){
        return Lists.newArrayList(repository.findAll());
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get a devices by Id")
    public Devices getDeviceById(@ApiParam(value = "Id device", required = true) @PathVariable(value = "id") int id) {

        devices = Lists.newArrayList(repository.findAll());
        Devices dev = repository.findById(devices.get(id).getId()).get();
        return dev;
    }

    @PostMapping("/")
    @ApiOperation(value = "Add device")
    public Devices createDevevice(
            @ApiParam(value = "Device object", required = true)
            @Valid @RequestBody Devices devices){
        return repository.save(devices);
    }


}
