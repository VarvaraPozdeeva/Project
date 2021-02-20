package com.netcracker.services.api;

import com.netcracker.model.documents.HardwareComponent;
import com.netcracker.model.documents.Interface;
import com.netcracker.model.documents.NetworkElement;
import com.netcracker.model.documents.SubInterface;
import com.netcracker.model.edges.Link;

import java.io.IOException;
import java.util.List;

public interface InventoryService {
    List<NetworkElement> getNetworkElements();

    NetworkElement storeNetworkElement(NetworkElement networkElement);

    NetworkElement getNetworkElementById(String networkElementId);

    NetworkElement removeNetworkElementById(String id);

    HardwareComponent getHardwareComponent(String networkElementId);

    HardwareComponent storeHardwareComponent(String networkElementId, HardwareComponent component);

    List<Interface> getInterfaces(String networkElementId);

    Interface storeInterface(String networkElementId, Interface inter);

    List<SubInterface> getSubInterfaces(String interfaceId);

    SubInterface storeSubInterface(String interfaceId, SubInterface subInterface);

    List<Link> getLinks(String networkElementId);

    Link storeLink(Link link);

    HardwareComponent deleteHardwareComponent(String id);

    Link deleteLink(String id);

    List<Link> getAllLinks();

    Interface deleteInterfaces(String id);

    String storeData(String data) throws IOException;
}

