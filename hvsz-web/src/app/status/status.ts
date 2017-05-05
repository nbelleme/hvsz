import {GameState} from "../game/game-state";
import {Life} from "../life/life";

/**
 * Created by nicolas on 05/05/2017.
 */
export class Status {

  private maxHumansOnField: number;
  private currentHumansOnField: number;
  private remainingHumanTickets: number;
  private remainingTime: number;
  private timestampStart: number;
  private started: boolean = false;
  private lives: Life[];
  private gameState: GameState = GameState.NOT_STARTED;
}
