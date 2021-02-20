package com.netcracker.services.impl;

import com.arangodb.ArangoCursor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.IOException;
import java.util.List;

import static com.netcracker.model.CollectionsNames.*;

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
    public NetworkElement removeNetworkElementById(String id) {
        NetworkElement networkElement = findNeById(id);

        deleteHardwareComponent(id);

        List<Interface>  interfaces = networkElement.getInterfaces();
        interfaces.forEach(inter -> {
            deleteInterfaces(inter.getId());
        });
        neRepository.delete(networkElement);
        return networkElement;
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
            throw new IllegalStateException
                    ("Network element with id: " + networkElementId + " already has assigned interface");
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


    @Override
    public HardwareComponent deleteHardwareComponent(String id) {
        NetworkElement networkElement = neRepository.findById(id).orElseThrow(
                ()-> new ObjectNotFoundException("Unable to find network element with id: " + id ));

        if(networkElement.getHardwareComponent() == null){
            throw new IllegalStateException
                    ("Network element with id: " + id + " has not hardware component");
        }

        HardwareComponent hwComponent = networkElement.getHardwareComponent();
        hwRepository.delete(hwComponent);

        neToHwRepository.removeNeToHwByNeId(getID(NETWORK_ELEMENT, id));

        return hwComponent;
    }



    @Override
    public Link deleteLink(String id) {

        String idLink = getID(LINK, id);
        Link link = linkRepository.findById(idLink)
                .orElseThrow(()-> new ObjectNotFoundException
                        ("Unable to find link with id: " + id));
        linkRepository.delete(link);
        return link;
    }

    @Override
    public List<Link> getAllLinks() {

        return Lists.newArrayList(linkRepository.findAll());
    }

    @Override
    public Interface deleteInterfaces(String id) {
        String idInterface = getID(INTERFACE, id);
        Interface inter = interfaceRepository.findById(idInterface)
                .orElseThrow(()->new ObjectNotFoundException
                        ("Unable to find interface with id :" + id));

       NeToInterface neToInt =  neToInterRepository.findNeIdByInterID(idInterface).asListRemaining()
               .stream().findFirst().orElseThrow(()-> new ObjectNotFoundException(("Unabled to find")));

       String neName = neToInt.getNe().getName();
       List<Link> links = linkRepository.findAllByNeNameAndInterName(neName, inter.getName()).asListRemaining();
       links.forEach(l->{deleteLink(l.getId());});
        neToInterRepository.removeNeToInterfaceByIntId(idInterface);

        List<SubInterface> sunInter =  inter.getSubInterfaces();
        sunInter.forEach(subRepository::delete);

        intToSubRepository.removeIntToSubByIntId(idInterface);

        interfaceRepository.delete(inter);

        return  inter;
    }

    @Override
    public String storeData(String data) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode nodes = mapper.readTree(data);

        for (JsonNode rootNode: nodes) {

            JsonNode hw = rootNode.path("hw-component");
            JsonNode ne = rootNode.path("network-element");
            JsonNode inter = rootNode.path("interface");
            JsonNode subInt = rootNode.path("sub-interface");

            NetworkElement netElem = mapper.treeToValue(ne, NetworkElement.class);
            HardwareComponent hwComp = mapper.treeToValue(hw, HardwareComponent.class);
            Interface[] interfaces = mapper.treeToValue(inter, Interface[].class);

            netElem = storeNetworkElement(netElem);
            storeHardwareComponent(netElem.getId(), hwComp);
            Interface in = null;
            for (Interface i : interfaces) {
                in = storeInterface(netElem.getId(), i);
            }

            if (!(subInt.isMissingNode())) {
                SubInterface[] subInterfaces = mapper.treeToValue(subInt, SubInterface[].class);
                for (SubInterface s : subInterfaces) {
                    storeSubInterface(in.getId(), s);
                }
            }
        }

        return "added";
    }

    private Interface findInterByNeIdAndName(String id, String intName) {
        return interfaceRepository.findInterfaceByNeIdAndName(id,intName)
                .asListRemaining().stream().findFirst()
                .orElseThrow(()->new ObjectNotFoundException
                        ("Unable to find interface with name: " + intName));
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
