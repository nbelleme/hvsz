import {Game} from './game';
import {Http, Headers} from '@angular/http';
import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';


/**
 * Created by nicolas on 05/05/2017.
 */
@Injectable()
export class GameService {
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

  getGame(): Promise<Game> {
    return this.http
      .get('localhost:8080/api/game', {headers : this.headers})
      .toPromise()
      .then(response => response.json().data as Game)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
