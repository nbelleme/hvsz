import {Game} from './game';
import {Http} from '@angular/http';
import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';


/**
 * Created by nicolas on 05/05/2017.
 */
@Injectable()
export class GameService {
  constructor(private http: Http) {
  }


  getGame(): Promise<Game> {
    return this.http
      .get('localhost:8080/api/game')
      .toPromise()
      .then(response => response.json().data as Game)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
