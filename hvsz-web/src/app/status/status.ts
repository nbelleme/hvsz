import {GameState} from "../game/game-state";
import {Life} from "../life/life";

/**
 * Created by nicolas on 05/05/2017.
 */
export class Status {

  private active : boolean;
  private currentHumansOnField: number;
  private gameState: GameState = GameState.NOT_STARTED;
  private lives: Life[];
  private maxHumansOnField: number;
  private remainingHumanTickets: number;
  private remainingTime: number;
  private timestampStart: number;
}
