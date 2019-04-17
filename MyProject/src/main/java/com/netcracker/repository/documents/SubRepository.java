package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.SubInterface;

public interface SubRepository extends ArangoRepository<SubInterface, String> {
}
