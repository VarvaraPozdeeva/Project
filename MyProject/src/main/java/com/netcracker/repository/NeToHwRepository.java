package com.netcracker.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.NeToHw;

public interface NeToHwRepository extends ArangoRepository<NeToHw, String> {
}
