package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.HardwareComponent;

public interface HwRepository extends ArangoRepository <HardwareComponent, String> {
}
