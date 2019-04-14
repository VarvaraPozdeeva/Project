package com.netcracker.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.NetworkElement;

public interface NeRepository extends ArangoRepository<NetworkElement, String> {
}
