import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import { CommonModule } from '@angular/common';

import { AppRoutingModule }   from './app-routing.module';

import {AppComponent} from './app.component';
import {GameComponent} from './game/game.component';
import {DashboardComponent} from './game/dashboard.component';
import {FoodSupplyComponent} from './foodsupply/food-supply.component';

import {DashboardService} from './game/dashboard.service';
import {GameService} from './game/game.service';
import {FoodSuppplyService} from './foodsupply/food-supply.service';


@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    DashboardComponent,
    FoodSupplyComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    CommonModule
  ],
  providers: [
      GameService,
      DashboardService,
      FoodSuppplyService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
