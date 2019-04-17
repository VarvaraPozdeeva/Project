package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.Interface;

public interface InterfaceRepository extends ArangoRepository<Interface, String> {
}
