/**
 * Created by nicolas on 05/05/2017.
 */
export class GameSettings {

  private _gameDuration:number;
  private _difficulty:number;
  private _humanTickets:number;
  private _maxHumansOnField:number;
  private _nbSafeZones:number;
  private _nbFoodSupplyZones:number;
  private _nbFoodSupplies:number;
  private _maximumFoodTransfer:number;
  private _safeZoneDropRate:number;

  get gameDuration(): number {
    return this._gameDuration;
  }

  set gameDuration(value: number) {
    this._gameDuration = value;
  }

  get difficulty(): number {
    return this._difficulty;
  }

  set difficulty(value: number) {
    this._difficulty = value;
  }

  get humanTickets(): number {
    return this._humanTickets;
  }

  set humanTickets(value: number) {
    this._humanTickets = value;
  }

  get maxHumansOnField(): number {
    return this._maxHumansOnField;
  }

  set maxHumansOnField(value: number) {
    this._maxHumansOnField = value;
  }

  get nbSafeZones(): number {
    return this._nbSafeZones;
  }

  set nbSafeZones(value: number) {
    this._nbSafeZones = value;
  }

  get nbFoodSupplyZones(): number {
    return this._nbFoodSupplyZones;
  }

  set nbFoodSupplyZones(value: number) {
    this._nbFoodSupplyZones = value;
  }

  get nbFoodSupplies(): number {
    return this._nbFoodSupplies;
  }

  set nbFoodSupplies(value: number) {
    this._nbFoodSupplies = value;
  }

  get maximumFoodTransfer(): number {
    return this._maximumFoodTransfer;
  }

  set maximumFoodTransfer(value: number) {
    this._maximumFoodTransfer = value;
  }


  get safeZoneDropRate(): number {
    return this._safeZoneDropRate;
  }

  set safeZoneDropRate(value: number) {
    this._safeZoneDropRate = value;
  }
}
