package com.netcracker.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.NeToInterface;

public interface NeToInterRepository extends ArangoRepository<NeToInterface, String> {
}
