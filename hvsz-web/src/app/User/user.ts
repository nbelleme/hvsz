import {UserType} from './user-type';

/**
 * Created by nicolas on 14/05/2017.
 */
export class User {
  private _userType: UserType;
  public readonly maxLoad: number = 8;
  private _currentLoad: number;


  constructor() {
    this._currentLoad = 0;
    this._userType = UserType.HUMAN;
  }

  get userType(): UserType {
    return this._userType;
  }

  get currentLoad(): number {
    return this._currentLoad;
  }

  loadFood(): boolean {
    if (this._currentLoad <= this.maxLoad - 1) {
      this._currentLoad++;
      return true;
    }
    return false;
  }

  isFull(): boolean{
    return this._currentLoad == this.maxLoad;
  }

}
