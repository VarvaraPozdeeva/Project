package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.Link;

public interface LinkRepository extends ArangoRepository<Link, String> {
}
