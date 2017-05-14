import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {FoodSupply} from "./food-supply";

@Injectable()
export class FoodSuppplyService {
    private foodSupplyUrl = 'http://'+window.location.hostname+':8080/api/food-supply';  // URL to web api
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) {

    }

    getZone(id: number): Promise<FoodSupply> {
        const url = `${this.foodSupplyUrl}/${id}`;
        return this.http.get(url, {headers : this.headers})
            .toPromise()
            .then(response => response.json() as FoodSupply)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        return Promise.reject(error.message || error);
    }

}
