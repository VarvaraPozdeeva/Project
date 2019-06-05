package com.netcracker.repository.edges;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.NeToInterface;
import org.springframework.data.repository.query.Param;

public interface NeToInterRepository extends ArangoRepository<NeToInterface, String> {

    @Query("FOR ne IN `ne-to-interface` FILTER ne._to == @intID REMOVE {  _key: ne._key } IN `ne-to-interface`")
    void removeNeToInterfaceByIntId(@Param("intID")String intID);

    @Query(" for ne IN `ne-to-interface` FILTER ne._to == @intID RETURN ne")
    ArangoCursor<NeToInterface> findNeIdByInterID(@Param("intID") String intID);
}
