import { Component, OnInit } from '@angular/core';
import { Player } from 'src/models/player.model';
import { Team } from 'src/models/team.model';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  teams:Team[]=[];
  players:Player[]=[];

  newTeam: Team={name:'',maximumBudget:1};
  newPlayer: Player={name: '',age:0,category:'',biddingPrice:0};

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.getTeams();
    this.getPlayers();
  }

  getTeams():void{
    this.adminService.getAllTeams().subscribe((data)=>{
      this.teams= data;
    });
  }

  createTeam(newTeam:Team):void{
    if(newTeam.name && newTeam.maximumBudget){
      this.adminService.addTeam(newTeam).subscribe(()=>{
        console.log('New Team'+newTeam);
        this.getTeams();
      })
    }
  }

  saveEditedTeam(team:Team):void{
    if(team){
      this.adminService.updateTeam(team.id, team).subscribe(()=>{
        this.getTeams();
      });
    }
  }

  deleteTeam(teamId:number):void{
    this.adminService.deleteTeamById(teamId).subscribe(()=>{
      this.getTeams();
    });
  }

  getPlayers():void{
    this.adminService.getPlayer().subscribe((data)=>{
      this.players=data;
    });
  }

  createPlayer(newPlayer:Player):void{
    console.log(newPlayer);
    this.adminService.addPlayer(newPlayer).subscribe(()=>{
      this.getPlayers();
    });
  }

  saveEditedPlayer(player:Player):void{
    if(player){
      this.adminService.updatePlayer(player.id, player).subscribe(()=>{
        this.getPlayers();
      });
    }
  }

  deletePlayer(playerId: number):void{
    this.adminService.deletePlayerById(playerId).subscribe(()=>{
      this.getPlayers();
    });
  }
}
