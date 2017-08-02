package io.nbelleme.persistence.dao.api.repository;

import io.nbelleme.hvsz.game.internal.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {

}
