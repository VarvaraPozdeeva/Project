package com.netcracker.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.Link;

public interface LinkRepository extends ArangoRepository<Link, String> {
}
