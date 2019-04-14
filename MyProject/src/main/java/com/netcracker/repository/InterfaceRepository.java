package com.netcracker.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.Interface;

public interface InterfaceRepository extends ArangoRepository<Interface, String> {
}
