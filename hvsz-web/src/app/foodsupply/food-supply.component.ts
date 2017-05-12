import "rxjs/add/operator/switchMap";
import {Component, OnInit} from "@angular/core";
import {FoodSuppplyService} from "./food-supply.service";
import {FoodSupply} from "./food-supply";
import {ActivatedRoute, Params} from "@angular/router";

@Component({
    selector: 'food-supply',
    templateUrl: './food-supply.component.html',
    styleUrls: [ './food-supply.component.css' ]
})
export class FoodSupplyComponent implements OnInit {
    foodSupply : FoodSupply;
    constructor(
        private foodSupplyService: FoodSuppplyService,
        private route: ActivatedRoute
    ) {}
    ngOnInit(): void {
        this.route.params
            .switchMap((params: Params) => this.foodSupplyService.getZone(+params['id']))
            .subscribe(
              foodSupply => this.foodSupply = foodSupply);
    }
}
