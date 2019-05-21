package com.netcracker.repository.documents;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.NetworkElement;
import org.springframework.data.repository.query.Param;

import static com.netcracker.model.CollectionsNames.NETWORK_ELEMENT;

public interface NeRepository extends ArangoRepository<NetworkElement, String> {

    @Query("FOR ne IN `" + NETWORK_ELEMENT + "` FILTER ne.name == @neName RETURN ne")
    ArangoCursor<NetworkElement> findByName(@Param("neName")String neName);

}
