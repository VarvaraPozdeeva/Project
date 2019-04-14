package com.netcracker.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.HwToHw;

public interface HwToHwRepository extends ArangoRepository<HwToHw, String> {
}
