package com.netcracker.repository.edges;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.Link;
import org.springframework.data.repository.query.Param;

import static com.netcracker.model.CollectionsNames.LINK;

public interface LinkRepository extends ArangoRepository<Link, String> {
    @Query("FOR link IN link FILTER"+
            " link.`a-ne` == @aNe &&  link.`z-ne` == @zNe &&"+
            " link.`a-interface` == @aInt && link.`z-interface` == @zInt"+
            " RETURN link" )
    ArangoCursor<Link> findLinkByEndPoints(@Param("aNe")String aNe,
                                           @Param("zNe")String zNe,
                                           @Param("aInt")String aInt,
                                           @Param("zInt")String zInt);

    @Query("for link IN `link` filter link.`a-ne` == @neName return link")
    ArangoCursor<Link> findAllByNeName(@Param("neName")String neName);

}
