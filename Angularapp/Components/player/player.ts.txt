import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Player } from 'src/models/player.model';


@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {
  
  @Input() players: Player[] = [];
  @Output() createPlayerEvent = new EventEmitter<Player>();
  @Output() editPlayerEvent = new EventEmitter<Player>();
  @Output() deletePlayerEvent = new EventEmitter<number>();

  isEditing: boolean = false;
  newPlayer: Player = {};
  categories:string[]=['Batsman','All Rounder','Bowler','Wicket Keeper'];

  constructor() { }

  ngOnInit(): void {
  }

  createPlayer() {
    if (!this.isEditing) {
      if (this.newPlayer.name && this.newPlayer.age && this.newPlayer.category && this.newPlayer.biddingPrice) {
        this.createPlayerEvent.emit(this.newPlayer);
        this.newPlayer = {};
      }
    } else {
      this.onSaveEditedPlayer();
    }
  }

  onEditPlayer(player: Player) {
    this.newPlayer = { ...player };
    this.isEditing = true;

  }

  onSaveEditedPlayer() {
    if (this.newPlayer.name && this.newPlayer.age && this.newPlayer.category && this.newPlayer.biddingPrice) {
      this.editPlayerEvent.emit(this.newPlayer);
      this.newPlayer = {};
      this.isEditing = false;
    }
  }

  onCancelEditPlayer() {
    this.newPlayer = {};
    this.isEditing = false;
  }

  onDeletePlayer(playerId: number) {
    if(confirm("Are you sure you want to delete this player?")){
      this.deletePlayerEvent.emit(playerId);
    }
  }

  biddingPriceStatus(): any {
    if (!this.newPlayer.biddingPrice) return 0;
    return this.newPlayer.biddingPrice > 100000 ? 'High' : 'Good Bidding';
  }
}


