package com.netcracker.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.HardwareComponent;

public interface HwRepository extends ArangoRepository <HardwareComponent, String> {
}
