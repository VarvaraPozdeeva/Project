package com.netcracker.controller;

import com.google.common.collect.Lists;
import com.netcracker.model.Devices;
import com.netcracker.repository.DevRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/data")
@Api(value = "Network system")
@RequiredArgsConstructor
public class InventoryController {

    private final DevRepository repository;

    @GetMapping("/devices")
    @ApiOperation(value = "Show all devices ")
    public List<Devices> getDevices(){
        return Lists.newArrayList(repository.findAll());
    }


    @GetMapping("/devices/{id}")
    @ApiOperation(value = "Get device by id")
    public Devices getDeviceById(@ApiParam(value = "Device id", required = true) @PathVariable String id) {

        return repository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Unable to find device with id: " + id));
    }

    @PostMapping("/devices")
    @ApiOperation(value = "Add device")
    public Devices createDevevice(
            @ApiParam(value = "Device object", required = true)
            @Valid @RequestBody Devices devices){
        return repository.save(devices);
    }


}
