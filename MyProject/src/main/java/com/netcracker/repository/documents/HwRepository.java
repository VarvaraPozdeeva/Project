package com.netcracker.repository.documents;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.HardwareComponent;
import org.springframework.data.repository.query.Param;

import static com.netcracker.model.CollectionsNames.NE_TO_HW;

public interface HwRepository extends ArangoRepository <HardwareComponent, String> {

    @Query("FOR hw IN 1..1 OUTBOUND @neId `" + NE_TO_HW + "` RETURN hw")
    ArangoCursor<HardwareComponent> getHwByNeId(@Param("neId") String neId);
}
