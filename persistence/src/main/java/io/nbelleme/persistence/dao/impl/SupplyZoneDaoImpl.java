package io.nbelleme.persistence.dao.impl;

import io.nbelleme.hvsz.zone.internal.SupplyZone;
import io.nbelleme.persistence.dao.api.SupplyZoneDao;
import io.nbelleme.persistence.dao.api.repository.SupplyZoneRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class SupplyZoneDaoImpl implements SupplyZoneDao{


  private SupplyZoneRepository repository;

  /**
   * Constructor.
   * @param repository repository
   */
  public SupplyZoneDaoImpl(SupplyZoneRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }


  @Override
  public Optional<SupplyZone> update(SupplyZone supplyZone) {
    Objects.requireNonNull(supplyZone);
    return Optional.ofNullable(repository.save(supplyZone));
  }

  @Override
  public Optional<SupplyZone> insert(SupplyZone supplyZone) {
    Objects.requireNonNull(supplyZone);
    return Optional.ofNullable(repository.insert(supplyZone));
  }
}
