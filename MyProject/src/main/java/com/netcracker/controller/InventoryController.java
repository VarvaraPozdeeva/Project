package com.netcracker.controller;

import com.netcracker.exceptions.ObjectNotFoundException;
import com.netcracker.model.documents.HardwareComponent;
import com.netcracker.model.documents.Interface;
import com.netcracker.model.documents.NetworkElement;
import com.netcracker.model.documents.SubInterface;
import com.netcracker.model.edges.IntToSub;
import com.netcracker.model.edges.Link;
import com.netcracker.model.edges.NeToHw;
import com.netcracker.model.edges.NeToInterface;

import com.netcracker.repository.documents.HwRepository;
import com.netcracker.repository.documents.InterfaceRepository;
import com.netcracker.repository.documents.NeRepository;
import com.netcracker.repository.documents.SubRepository;
import com.netcracker.repository.edges.IntToSubRepository;
import com.netcracker.repository.edges.LinkRepository;
import com.netcracker.repository.edges.NeToHwRepository;
import com.netcracker.repository.edges.NeToInterRepository;
import com.netcracker.services.api.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/data")
@Api(value = "Network inventory service")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/interfaces/{id}")
    @ApiOperation(value = "Show all interface by network element id ")
    public Iterable<Interface> getInterfaces(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id) {

        return inventoryService.getInterfaces(id);
    }
    @PostMapping("/interfaces/{id}")
    @ApiOperation(value = "Add interface")
    public Interface createInterface(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id,
            @ApiParam(value = "interface", required = true)
            @RequestBody Interface inter ){
        return inventoryService.storeInterface(id, inter);
    }


    @GetMapping("/sub-interfaces/{id}")
    @ApiOperation(value = "Show all sub interface by id interface")
    public Iterable<SubInterface> getSubInterfaces(
            @ApiParam(value = "interface id", required = true)
            @PathVariable String id) {
        return inventoryService.getSubInterfaces(id);
    }
    @PostMapping("/sub-interfaces/{id}")
    @ApiOperation(value = "Add sub interface")
    public  SubInterface createSubInterface(
            @ApiParam(value = "Interface id", required = true)
            @PathVariable String id,
            @ApiParam(value = "sub interface object", required = true)
            @RequestBody SubInterface subInter){

        return inventoryService.storeSubInterface(id, subInter);
    }

    @GetMapping("/links/{id}")
    @ApiOperation(value = "Get all link by network element id")
    public List<Link> getLinkById(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id) {
        return inventoryService.getLinks(id);
    }
    @PostMapping("/links")
    @ApiOperation(value = "Add link")
    public Link createLink(
            @ApiParam(value = "Link object", required = true)
            @RequestBody Link ln){

        return inventoryService.storeLink(ln);
    }

    @GetMapping("/hardware-components/{id}")
    @ApiOperation(value = "Get hardware component by network element id")
    public HardwareComponent getHwComponent(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id) {

        return inventoryService.getHardwareComponent(id);
    }
    @PostMapping("/hw-components/{id}")
    @ApiOperation(value = "Add hardware component")
    public HardwareComponent createHardwareComponent(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id,
            @ApiParam(value = "hardware component object", required = true)
            @RequestBody HardwareComponent hardwareCom){

        return inventoryService.storeHardwareComponent(id, hardwareCom);
    }

    @GetMapping("/network-elements")
    @ApiOperation(value = "Show all network element ")
    public List<NetworkElement> getNetElements(){

        return inventoryService.getNetworkElements();
    }

    @GetMapping("/network-elements/{id}")
    @ApiOperation(value = "Get network element by id")
    public NetworkElement getNetElementById(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id) {

        return inventoryService.getNetworkElementById(id);
    }
    @PostMapping("/network-elements")
    @ApiOperation(value = "Add network element")
    public NetworkElement createNetworkElement(
            @ApiParam(value = "network element object", required = true)
            @RequestBody NetworkElement networkEl){

        return inventoryService.storeNetworkElement(networkEl);
    }


}
