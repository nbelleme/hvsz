import {Status} from '../status/status';
import {GameSettings} from '../game-settings/game-settings';
import {SafeZone} from '../safe-zone/safe-zone';
import {FoodSupply} from '../foodsupply/food-supply';

/**
 * Created by nicolas on 05/05/2017.
 */
export class Game {
  private id: number;
  private status: Status;
  private config: GameSettings;
  private safeZones: SafeZone[];
  private foodSupplies: FoodSupply[];
}
