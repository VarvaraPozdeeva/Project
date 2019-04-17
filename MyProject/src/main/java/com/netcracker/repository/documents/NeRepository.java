package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.NetworkElement;

public interface NeRepository extends ArangoRepository<NetworkElement, String> {
}
