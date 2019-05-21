package com.netcracker.services.impl;

import com.arangodb.ArangoCursor;
import com.google.common.collect.Lists;
import com.netcracker.exceptions.ObjectNotFoundException;
import com.netcracker.model.documents.*;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.netcracker.model.CollectionsNames.INTERFACE;
import static com.netcracker.model.CollectionsNames.NETWORK_ELEMENT;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InterfaceRepository interfaceRepository;
    private final HwRepository hwRepository;
    private final NeRepository neRepository;
    private final NeToHwRepository neToHwRepository;
    private final NeToInterRepository neToInterRepository;
    private final LinkRepository linkRepository;
    private final SubRepository subRepository;
    private final IntToSubRepository intToSubRepository;

    @Override
    public List<NetworkElement> getNetworkElements() {
        return Lists.newArrayList(neRepository.findAll());
    }

    @Override
    public NetworkElement storeNetworkElement(NetworkElement networkElement) {
        return neRepository.save(networkElement);
    }

    @Override
    public NetworkElement getNetworkElementById(String networkElementId) {
        return findNeById(networkElementId);
    }

    @Override
    public HardwareComponent getHardwareComponent(String networkElementId) {
        String neId = getID(NETWORK_ELEMENT, networkElementId);
        ArangoCursor<HardwareComponent> cursor = hwRepository.getHwByNeId(neId);

        return cursor.asListRemaining().stream().findFirst()
                .orElseThrow(()-> new ObjectNotFoundException
                ("Unable to found hardware component for network element with id: " + networkElementId));
    }

    @Override
    public HardwareComponent storeHardwareComponent(String networkElementId, HardwareComponent component) {

        NetworkElement networkElement = neRepository.findById(networkElementId).orElseThrow(
                ()-> new ObjectNotFoundException("Unable to find network element with id: " + networkElementId ));

        if(networkElement.getHardwareComponent() != null){
            throw new IllegalStateException
                    ("Network element with id: " + networkElementId + " already has assigned hardware component");
        }

        HardwareComponent hwComponent = hwRepository.save(component);

        NeToHw neToHw = new NeToHw(networkElement, hwComponent);

        neToHwRepository.save(neToHw);

        return hwComponent;
    }

    @Override
    public List<Interface> getInterfaces(String networkElementId) {
        String neId = getID(NETWORK_ELEMENT, networkElementId);
        return interfaceRepository.findInterfacesByNeId(neId).asListRemaining();
    }

    @Override
    public Interface storeInterface(String networkElementId, Interface inter) {

        NetworkElement networkElement = neRepository.findById(networkElementId).orElseThrow(
                ()-> new ObjectNotFoundException("Unable to find network element with id: " + networkElementId ));

        if(networkElement.getInterfaces().contains(inter)){
            throw new IllegalStateException("Network element with id: " + networkElementId + " already has assigned interface");
        }

        Interface anInterface = interfaceRepository.save(inter);

        NeToInterface neToInterface = new NeToInterface(networkElement, anInterface);

        neToInterRepository.save(neToInterface);

        return anInterface;
    }

    @Override
    public List<SubInterface> getSubInterfaces(String interfaceId) {

        String intId = getID(INTERFACE, interfaceId);
        return subRepository.findByIntId(intId).asListRemaining();
    }

    @Override
    public SubInterface storeSubInterface(String interfaceId, SubInterface subInterface) {
        Interface inter = interfaceRepository.findById(interfaceId).orElseThrow(
                ()->new ObjectNotFoundException("Unable to find interface with id: " + interfaceId));
        if(inter.getSubInterfaces().contains(subInterface)){
            throw  new IllegalStateException
                    ("Interface with id: " + interfaceId + " already has assigned sub interface");
        }
        SubInterface subInter = subRepository.save(subInterface);

        IntToSub intToSub = new IntToSub(inter, subInter);
        intToSubRepository.save(intToSub);

        return subInter;
    }

    @Override
    public List<Link> getLinks(String networkElementId) {

        String neName = findNeById(networkElementId).getName();
        return  linkRepository.findAllByNeName(neName).asListRemaining();
    }

    @Override
    public Link storeLink(Link link) {
        String aNeName = link.getNeAName();
        String zNeName = link.getNeZName();
        String aIntName = link.getInterAName();
        String zIntName = link.getInterZName();

        linkRepository.findLinkByEndPoints(aNeName, zNeName, aIntName, zIntName).asListRemaining()
                .stream().findFirst()
                .ifPresent(existedLink->{
                    throw new IllegalStateException("Link between " + aNeName + "|" + aIntName +
                            " and " + zNeName + "|" + zIntName + " already present.");
                });

        NetworkElement networkElementA = findNeByName(aNeName);
        NetworkElement networkElementZ = findNeByName(zNeName);
        Interface aInter = findInterByNeIdAndName(getID(NETWORK_ELEMENT,networkElementA.getId()), aIntName);
        Interface zInter = findInterByNeIdAndName(getID(NETWORK_ELEMENT, networkElementZ.getId()), zIntName);



        return linkRepository.save(Link.builder()
                .neAName(aNeName).neZName(zNeName)
                .interfaceA(aInter).interfaceZ(zInter)
                .interAName(aIntName).interZName(zIntName).build());
    }




    private Interface findInterByNeIdAndName(String id, String intName) {
        return interfaceRepository.findInterfaceByNeIdAndName(id,intName)
                .asListRemaining().stream().findFirst()
                .orElseThrow(()->new ObjectNotFoundException("Unable to find interface with name: " + intName));
    }

    private NetworkElement findNeByName(String neName) {
        return neRepository.findByName(neName)
                .asListRemaining().stream().findFirst()
                .orElseThrow(()-> new ObjectNotFoundException
                        ("Unable to find network element with name : " + neName ));
    }

    private static String getID(String collection, String key){
        return collection + "/" + key;
    }

    private NetworkElement findNeById(String networkElementId) {

      return  neRepository.findById(networkElementId).orElseThrow(()-> new ObjectNotFoundException
                ("Unable to find network element with id : " + networkElementId ));
    }


}
