import {Component, OnInit} from "@angular/core";
import {DashboardService} from "./dashboard.service";
import {FoodSupply} from "../foodsupply/food-supply";
import {Game} from "./game";

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./game.component.css']
})
export class DashboardComponent implements OnInit {
    foodSupply: FoodSupply;
    game: Game;

    constructor(private dashboardService: DashboardService) {
    }

    ngOnInit(): void {
        this.dashboardService.getGame().then(game => this.game = game);
    }
}
