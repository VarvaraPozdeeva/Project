package com.netcracker.repository.documents;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.SubInterface;
import org.springframework.data.repository.query.Param;

import static com.netcracker.model.CollectionsNames.INT_TO_SUB;

public interface SubRepository extends ArangoRepository<SubInterface, String> {

    @Query("FOR sub IN 1..1 OUTBOUND @intID `" + INT_TO_SUB + "` RETURN sub")
    ArangoCursor<SubInterface> findByIntId(@Param("intID") String intId);
}
