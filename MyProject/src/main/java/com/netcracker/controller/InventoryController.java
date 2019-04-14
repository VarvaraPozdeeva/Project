package com.netcracker.controller;

import com.google.common.collect.Lists;
import com.netcracker.model.*;
import com.netcracker.repository.*;

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

    private final InterfaceRepository interfaceRepository;
    private final HwRepository hwRepository;
    private final NeRepository neRepository;
    private final NeToHwRepository neToHwRepository;
    private final NeToInterRepository neToInterRepository;
    private final HwToHwRepository hwToHwRepository;
    private final LinkRepository linkRepository;

    @GetMapping("/link")
    @ApiOperation(value = "Show all link ")
    public List<Link> getLinks(){
        return Lists.newArrayList(linkRepository.findAll());
    }

    @GetMapping("/interface")
    @ApiOperation(value = "Show all interface ")
    public List<Interface> getInterfaces(){
        return Lists.newArrayList(interfaceRepository.findAll());
    }

    @GetMapping("/hardwareComponent")
    @ApiOperation(value = "Show all hardware component ")
    public List<HardwareComponent> getHwComponents(){
        return Lists.newArrayList(hwRepository.findAll());
    }

    @GetMapping("/networkElement")
    @ApiOperation(value = "Show all network element ")
    public List<NetworkElement> getNetElements(){
        return Lists.newArrayList(neRepository.findAll());
    }

    @GetMapping("/neToHw")
    @ApiOperation(value = "Show all ne-to-hw ")
    public List<NeToHw> getNeToHw(){
        return Lists.newArrayList(neToHwRepository.findAll());
    }

    @GetMapping("/neToInterface")
    @ApiOperation(value = "Show all ne-to-interface ")
    public List<NeToInterface> getNeToInterface(){
        return Lists.newArrayList(neToInterRepository.findAll());
    }

    @GetMapping("/hwToHw")
    @ApiOperation(value = "Show all hw-to-hw ")
    public List<HwToHw> getHwToHw(){
        return Lists.newArrayList(hwToHwRepository.findAll());
    }

    @GetMapping("/interface/{id}")
    @ApiOperation(value = "Get interface by id")
    public Interface getInterfaceById(
            @ApiParam(value = "interface id", required = true)
            @PathVariable String id) {

        return interfaceRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find interface with id: " + id));
    }
    @GetMapping("/hardwareComponent/{id}")
    @ApiOperation(value = "Get hardware component by id")
    public HardwareComponent getHwComponentById(
            @ApiParam(value = "hardware component id", required = true)
            @PathVariable String id) {

        return hwRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find hardware component with id: " + id));
    }

    @GetMapping("/networkElement/{id}")
    @ApiOperation(value = "Get network element by id")
    public NetworkElement getNetElementById(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id) {

        return neRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find network element with id: " + id));
    }

    @GetMapping("/hwToHw/{id}")
    @ApiOperation(value = "Get hw-to-hw by id")
    public HwToHw getHwToHwById(
            @ApiParam(value = "hw-to-hw id", required = true)
            @PathVariable String id) {

        return hwToHwRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find hw-to-hw with id: " + id));
    }

    @GetMapping("/neToHw/{id}")
    @ApiOperation(value = "Get ne-to-hw by id")
    public NeToHw getNeToHwById(
            @ApiParam(value = "ne-to-hw id", required = true)
            @PathVariable String id) {

        return neToHwRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find ne-to-hw with id: " + id));
    }

    @GetMapping("/link/{id}")
    @ApiOperation(value = "Get link by id")
    public Link getLinkById(
            @ApiParam(value = "link id", required = true)
            @PathVariable String id) {

        return linkRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find link with id: " + id));
    }


    @PostMapping("/interface")
    @ApiOperation(value = "Add interface")
    public @Valid Interface createInterface(
            @ApiParam(value = "interface object", required = true)
            @Valid @RequestBody Interface inter){

        return interfaceRepository.save(inter);
    }

    @PostMapping("/hardwareComponent")
    @ApiOperation(value = "Add hardware component")
    public HardwareComponent createHardwareComponent(
            @ApiParam(value = "hardware component object", required = true)
            @Valid @RequestBody HardwareComponent hardwareCom){

        return hwRepository.save(hardwareCom);
    }

    @PostMapping("/networkElement")
    @ApiOperation(value = "Add network element")
    public NetworkElement createNetworkElement(
            @ApiParam(value = "network element object", required = true)
            @Valid @RequestBody NetworkElement networkEl){

        return neRepository.save(networkEl);
    }

    @PostMapping("/neToHw")
    @ApiOperation(value = "Add ne-to-hw")
    public NeToHw createNeToHw(
            @ApiParam(value = "ne-to-hw object", required = true)
            @Valid @RequestBody NeToHw neToHw){

        return neToHwRepository.save(neToHw);
    }

    @PostMapping("/neToInterface")
    @ApiOperation(value = "Add ne-to-interface")
    public NeToInterface createNeToInterface(
            @ApiParam(value = "ne-to-interface object", required = true)
            @Valid @RequestBody NeToInterface neToInterface){

        return neToInterRepository.save(neToInterface);
    }

    @PostMapping("/hwToHw")
    @ApiOperation(value = "Add hw-to-hw")
    public HwToHw createHwToHw(
            @ApiParam(value = "hw-to-hw object", required = true)
            @Valid @RequestBody HwToHw hwToHw){

        return hwToHwRepository.save(hwToHw);
    }

    @PostMapping("/link")
    @ApiOperation(value = "Add link")
    public Link createLink(
            @ApiParam(value = "link object", required = true)
            @Valid @RequestBody Link link){

        return linkRepository.save(link);
    }
}
