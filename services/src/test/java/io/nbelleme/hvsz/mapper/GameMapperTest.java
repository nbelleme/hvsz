package io.nbelleme.hvsz.mapper;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.transfer.GameDTO;
import io.nbelleme.hvsz.mapper.api.GameMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class GameMapperTest {

  public GameMapperTest() {
  }

  private void check(Game game, GameDTO gameDTO) {
    Assert.assertEquals(game.getId(), gameDTO.getId());
  }

  @Test
  public void toDTO() {
    Game game = Game.build();

    GameDTO gameDTO = GameMapper.toDTO(game);

    check(game, gameDTO);
  }

}