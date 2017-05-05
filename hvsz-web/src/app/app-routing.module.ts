import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {GameComponent} from "./game/game.component";
import {DashboardComponent} from "./game/dashboard.component";
import {FoodSupplyComponent} from "./foodsupply/food-supply.component";

const routes: Routes = [
    {path: 'game', component: GameComponent},
    {path: 'dashboard', component: DashboardComponent},
    {path: 'food-supply/:id', component: FoodSupplyComponent}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
