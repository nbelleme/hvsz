package io.nbelleme.persistence.dao.api;

import io.nbelleme.hvsz.game.internal.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameDao extends MongoRepository<Game, String> {

}
