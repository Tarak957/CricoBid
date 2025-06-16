import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from 'src/models/player.model';
import { Team } from 'src/models/team.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  
public baseUrl='https://ide-eebaaeeaec326817061edeccfaaefdtwo.premiumproject.examly.io/proxy/8080';
constructor(private httpClient:HttpClient) { }

//!==================Team================
  public addTeam(team:Team):Observable<any>{
    return this.httpClient.post(this.baseUrl+'/api/team',team);
  }

  public updateTeam(teamId:number,team:Team):Observable<any>{
    return this.httpClient.put(this.baseUrl+'/api/team/'+teamId,team);
  }

  public getAllTeams():Observable<Team[]>{
    return this.httpClient.get<Team[]>(this.baseUrl+'/api/team');
  }

  public deleteTeamById(teamId:number):Observable<any>{
    return this.httpClient.delete(this.baseUrl+'/api/team/'+teamId);
  }
  
  //!============Player=====================
  
  public addPlayer(player:Player):Observable<any>{
    return this.httpClient.post(this.baseUrl+'/api/player',player);
  }

  public getPlayer():Observable<any>{
    return this.httpClient.get(this.baseUrl+'/api/player');
  }

  public updatePlayer(playerId:number,player:Player):Observable<any>{
    return this.httpClient.put(this.baseUrl+'/api/player/'+playerId,player);
  }

  public deletePlayerById(playerId:number):Observable<any>{
    return this.httpClient.delete(this.baseUrl+'/api/player/'+playerId);
  } 
}
