import 'rxjs/add/operator/switchMap';
import {Component, OnInit} from '@angular/core';
import {Params} from '@angular/router';
import {GameService} from './game.service';
import {Game} from './game';


/**
 * Created by nicolas on 05/05/2017.
 */
@Component({
  selector: 'hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: [ './hero-detail.component.css' ]
})
export class GameComponent implements OnInit {
  game: Game;
  constructor(
    private gameService: GameService
  ) {}
  ngOnInit(): void {
     this.gameService.getGame().then(game => this.game = game);
  }
}
