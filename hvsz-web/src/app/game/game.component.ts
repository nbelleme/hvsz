import {Component, OnInit} from "@angular/core";
import {GameService} from "./game.service";
import {Game} from "./game";

@Component({
    selector: 'game',
    templateUrl: './game.component.html',
    styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
    game: Game;

    constructor(private gameService: GameService) {
    }

    ngOnInit(): void {
        this.gameService.getGame().then(game => this.game = game);
    }
}
