package com.netcracker.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.Devices;

public interface DevRepository extends ArangoRepository<Devices, String> {
}
