import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Player } from 'src/models/player.model';

@Injectable({
  providedIn: 'root'
})
export class OrganizerService {

private baseUrl='https://ide-eebaaeeaec326817061edeccfaaefdtwo.premiumproject.examly.io/proxy/8080';
  constructor(private httpClient:HttpClient) { }
  public getUnsoldPlayers():Observable<any>{
    return this.httpClient.get(this.baseUrl+"/api/organizer/unsold-players");
  }

  public getSoldPlayers():Observable<any>{
    return this.httpClient.get(this.baseUrl+"/api/organizer/sold-players");
  }

  public assignPlayerToTeam(teamId:number,playerId:number):Observable<any>{
    // console.log(teamId+" "+playerId);
    return this.httpClient.post(this.baseUrl+"/api/organizer/assign-player?teamId="+teamId+"&playerId="+playerId,"");
  }

  public releasePlayerFromTeam(playerId:number):Observable<any>{
    return this.httpClient.put(this.baseUrl+"/api/organizer/release-player/"+playerId,"");
  }

  public getPlayerListByTeamId(teamId:number):Observable<any>{
    return this.httpClient.get(this.baseUrl+"/api/organizer/player-list/"+teamId);
  }

  public getAllTeams():Observable<any>{
    return this.httpClient.get(this.baseUrl+"/api/organizer/team-list");
  }

  public getPlayersByCategory(category:string):Observable<any>{
    return this.httpClient.get(this.baseUrl+"/api/organizer/player-list/category/"+category);
  }
}

