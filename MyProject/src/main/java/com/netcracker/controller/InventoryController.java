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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/data")
@Api(value = "Network inventory service")
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


    @GetMapping("/links/{id}")
    @ApiOperation(value = "Get link by id")
    public Link getLinkById(
            @ApiParam(value = "link id", required = true)
            @PathVariable String id) {

        return linkRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException
                        ("Unable to find link with id: " + id));
    }

    @PostMapping("/interfaces/{id}")
    @ApiOperation(value = "Add interface")
    public Interface createInterface(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id,
            @ApiParam(value = "interface", required = true)
            @RequestBody Interface inter ){

        NetworkElement networkElement = neRepository.findById(id).orElseThrow(
                ()-> new ObjectNotFoundException("Unable to find network element with id: " + id ));

        if(networkElement.getInterfaces().contains(inter)){
            throw new IllegalStateException("Network element with id: " + id + " already has assigned interface");
        }

        Interface anInterface = interfaceRepository.save(inter);

        NeToInterface neToInterface = new NeToInterface(networkElement, anInterface);

        neToInterRepository.save(neToInterface);

        return anInterface;
    }


    @PostMapping("/hw-components/{id}")
    @ApiOperation(value = "Add hardware component")
    public HardwareComponent createHardwareComponent(
            @ApiParam(value = "network element id", required = true)
            @PathVariable String id,
            @ApiParam(value = "hardware component object", required = true)
            @RequestBody HardwareComponent hardwareCom){

        NetworkElement networkElement = neRepository.findById(id).orElseThrow(
                ()-> new ObjectNotFoundException("Unable to find network element with id: " + id ));

        if(networkElement.getHardwareComponent() != null){
            throw new IllegalStateException
                    ("Network element with id: " + id + " already has assigned hardware component");
        }

        HardwareComponent hwComponent = hwRepository.save(hardwareCom);

        NeToHw neToHw = new NeToHw(networkElement, hwComponent);

        neToHwRepository.save(neToHw);

        return hwComponent;
    }

    @PostMapping("/sub-interfaces/{id}")
    @ApiOperation(value = "Add sub interface")
    public  SubInterface createSubInterface(
            @ApiParam(value = "Interface id", required = true)
            @PathVariable String id,
            @ApiParam(value = "sub interface object", required = true)
            @RequestBody SubInterface subInter){

        Interface inter = interfaceRepository.findById(id).orElseThrow(
                ()->new ObjectNotFoundException("Unable to find interface with id: " + id));
        if(inter.getSubInterfaces().contains(subInter)){
            throw  new IllegalStateException
                    ("Interface with id: " + id + " already has assigned sub interface");
        }
        SubInterface subInterface = subRepository.save(subInter);

        IntToSub intToSub = new IntToSub(inter, subInterface);
        intToSubRepository.save(intToSub);

        return subInterface;
    }

    @PostMapping("/network-elements")
    @ApiOperation(value = "Add network element")
    public NetworkElement createNetworkElement(
            @ApiParam(value = "network element object", required = true)
            @RequestBody NetworkElement networkEl){

        return neRepository.save(networkEl);
    }

    @PostMapping("/links")
    @ApiOperation(value = "Add link")
    public Link createLink(
            @ApiParam(value = "Link object", required = true)
            @RequestBody Link ln){

        if(ln.getInterAId().equals(ln.getInterZId())){
            throw new IllegalArgumentException("Id must be different");
        }
        Interface aInter = interfaceRepository.findById(ln.getInterAId()).orElseThrow(
                ()->new ObjectNotFoundException("Unable to find interface with id: " + ln.getInterAId()));
        Interface zInter = interfaceRepository.findById(ln.getInterZId()).orElseThrow(
                ()->new ObjectNotFoundException("Unable to find interface with id: " + ln.getInterZId()));


       linkRepository.findAll().forEach((l)->{
           if(l.getInterfaceA().getName().equals(aInter.getName())&& l.getInterfaceZ().getName().equals(zInter.getName())
           || l.getInterfaceA().getName().equals(zInter.getName())&& l.getInterfaceZ().getName().equals(aInter.getName()))
              throw  new IllegalStateException("Link already exists");
       });

       Link link = new Link(aInter, zInter);
       link.setInterAId(aInter.getId());
       link.setInterZId(zInter.getId());

        neToInterRepository.findAll().forEach((neToInter)->{
            if(neToInter.getAnInterface().equals(aInter)){
                link.setNeAName(neToInter.getNe().getName());
            }
            if(neToInter.getAnInterface().equals(zInter)){
                link.setNeZName(neToInter.getNe().getName());
            }
        });
        return linkRepository.save(link);
    }
}
