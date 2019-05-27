package com.netcracker.repository.edges;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.NeToHw;
import org.springframework.data.repository.query.Param;

public interface NeToHwRepository extends ArangoRepository<NeToHw, String> {

    @Query("FOR hw IN `ne-to-hw` FILTER hw._from == @neID REMOVE {  _key: hw._key } IN `ne-to-hw`")
    void removeNeToHwByNeId(@Param("neID")String neID);
}
