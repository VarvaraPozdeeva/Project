package com.netcracker.repository.edges;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.IntToSub;
import org.springframework.data.repository.query.Param;

public interface IntToSubRepository extends ArangoRepository<IntToSub, String> {

    @Query("FOR sub IN `int-to-sub` FILTER sub._to == @intID REMOVE {  _key: sub._key } IN `int-to-sub`")
    void removeIntToSubByIntId(@Param("intID")String intID);
}
