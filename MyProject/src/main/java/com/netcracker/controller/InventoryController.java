package com.netcracker.controller;

import com.netcracker.model.documents.HardwareComponent;
import com.netcracker.model.documents.Interface;
import com.netcracker.model.documents.NetworkElement;
import com.netcracker.model.documents.SubInterface;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private final LinkRepository linkRepository;
    private final SubRepository subRepository;
    private final IntToSubRepository intToSubRepository;

    @GetMapping("/links")
    @ApiOperation(value = "Show all link ")
    public Iterable<Link> getLinks(){
        return linkRepository.findAll();
    }

    @GetMapping("/interfaces")
    @ApiOperation(value = "Show all interface ")
    public Iterable<Interface> getInterfaces(){

        return interfaceRepository.findAll();
    }
    @GetMapping("/sub-interfaces")
    @ApiOperation(value = "Show all sub interface ")
    public Iterable<SubInterface> getSubInterfaces(){

        return subRepository.findAll();
    }

    @GetMapping("/hw-components")
    @ApiOperation(value = "Show all hardware component ")
    public Iterable<HardwareComponent> getHwComponents(){

        return hwRepository.findAll();
    }

    @GetMapping("/network-elements")
    @ApiOperation(value = "Show all network element ")
    public Iterable<NetworkElement> getNetElements(){

        return neRepository.findAll();
    }

    @GetMapping("/ne-to-hw")
    @ApiOperation(value = "Show all ne-to-hw ")
    public Iterable<NeToHw> getNeToHw(){

        return neToHwRepository.findAll();
    }

    @GetMapping("/ne-to-interface")
    @ApiOperation(value = "Show all ne-to-interface ")
    public Iterable<NeToInterface> getNeToInterface(){

        return neToInterRepository.findAll();
    }

    @GetMapping("/interfaces/{id}")
    @ApiOperation(value = "Get interface by id")
    public Interface getInterfaceById(
            @ApiParam(value = "interface id", required = true)
            @PathVariable String id) {

        return interfaceRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find interface with id: " + id));
    }


    @GetMapping("/hardware-components/{id}")
    @ApiOperation(value = "Get hardware component by id")
    public HardwareComponent getHwComponentById(
            @ApiParam(value = "hardware component id", required = true)
            @PathVariable String id) {

        return hwRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find hardware component with id: " + id));
    }

    @GetMapping("/network-elements/{id}")
    @ApiOperation(value = "Get network element by id")
    public NetworkElement getNetElementById(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id) {

        return neRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find network element with id: " + id));
    }



    @GetMapping("/ne-to-hw/{id}")
    @ApiOperation(value = "Get ne-to-hw by id")
    public NeToHw getNeToHwById(
            @ApiParam(value = "ne-to-hw id", required = true)
            @PathVariable String id) {

        return neToHwRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find ne-to-hw with id: " + id));
    }

    @GetMapping("/links/{id}")
    @ApiOperation(value = "Get link by id")
    public Link getLinkById(
            @ApiParam(value = "link id", required = true)
            @PathVariable String id) {

        return linkRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find link with id: " + id));
    }

    @PostMapping("/network-elements/{id}/interface")
    @ApiOperation(value = "Add interface")
    public @Valid Interface createInterface(
            @ApiParam(value = "interface object", required = true)
            @Valid @RequestBody Interface inter, @PathVariable String id){

        return interfaceRepository.save(inter);
    }


    @PostMapping("/hardware-components")
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

    @PostMapping("/links")
    @ApiOperation(value = "Add link")
    public Link createLink(
            @ApiParam(value = "link object", required = true)
            @Valid @RequestBody Link link){

        return linkRepository.save(link);
    }
}
