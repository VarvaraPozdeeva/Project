package com.netcracker.database;

import com.arangodb.springframework.core.ArangoOperations;
import com.netcracker.model.Devices;
import com.netcracker.parsetoPOJOfromJSON.Parse;
import com.netcracker.repository.DevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan("com.netcracker")
public class CrudRunner implements CommandLineRunner {

    @Autowired private ArangoOperations operations;
    @Autowired private DevRepository repositoryDev;
    private List<Devices> devices = null;

    @Override
    public void run(String... args) throws Exception {

        operations.dropDatabase();
        devices = Parse.parsFromJson();
        repositoryDev.saveAll(devices);
        System.out.println(String.format("Devices saved in db with id: "));

        final Devices dev = repositoryDev.findById(devices.get(0).getId()).get();
        System.out.println(dev);
    }
}

