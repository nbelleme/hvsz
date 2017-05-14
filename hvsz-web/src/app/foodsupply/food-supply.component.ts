import 'rxjs/add/operator/switchMap';
import {Component, OnInit} from '@angular/core';
import {FoodSuppplyService} from './food-supply.service';
import {FoodSupply} from './food-supply';
import {ActivatedRoute, Params} from '@angular/router';
import {User} from '../User/user';

@Component({
  selector: 'food-supply',
  templateUrl: './food-supply.component.html',
  styleUrls: ['./food-supply.component.css']
})
export class FoodSupplyComponent implements OnInit {
  private foodSupply: FoodSupply;
  private mouseDownID: number = -1;
  private readonly TIMER_TAKE_FOOD = 1000;
  private currentUser: User = new User();

  constructor(private foodSupplyService: FoodSuppplyService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.foodSupplyService.getZone(+params['id']))
      .subscribe(
        foodSupply => this.foodSupply = foodSupply);
  }

  startTakeFood(): void {
    if (this.mouseDownID == -1) {
      this.mouseDownID = window.setInterval(
        () => this.takeFood()
        , this.TIMER_TAKE_FOOD);
    }
  }

  stopTakeFood(): void {
    if (this.mouseDownID != -1) {  //Only stop if exists
      clearInterval(this.mouseDownID);
      this.mouseDownID = -1;
    }
  }

  takeFood(): void {
    if (this.foodSupply.level > 0 && this.currentUser.loadFood()) {
      this.foodSupply.level--;
    }
  }

  get widthFoodLeft(): number {
    if (this.foodSupply.capacity != 0) {
      return this.foodSupply.level / this.foodSupply.capacity * 100;
    }

    return 0;
  }

  get widthCurrentLoad(): number {
    if (this.currentUser) {
      return this.currentUser.currentLoad / this.currentUser.maxLoad * 100;
    }

    return 0;
  }

  get isButtonDisabled(): boolean{
    return !(this.currentUser && !this.currentUser.isFull());
  }

}
