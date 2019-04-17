package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.NeToInterface;

public interface NeToInterRepository extends ArangoRepository<NeToInterface, String> {
}
