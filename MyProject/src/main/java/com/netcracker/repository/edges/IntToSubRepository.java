package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.IntToSub;

public interface IntToSubRepository extends ArangoRepository<IntToSub, String> {
}
