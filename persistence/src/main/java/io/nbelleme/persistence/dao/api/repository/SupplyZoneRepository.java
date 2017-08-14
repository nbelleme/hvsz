package io.nbelleme.persistence.dao.api.repository;

import io.nbelleme.hvsz.zone.internal.SupplyZone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplyZoneRepository extends MongoRepository<SupplyZone, String> {
}
