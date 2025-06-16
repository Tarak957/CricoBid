import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Team } from 'src/models/team.model';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {
  constructor() { }
  ngOnInit(): void { }

  @Input() teams: Team[] = [];
  @Output() createTeamEvent = new EventEmitter<Team>();
  @Output() editTeamEvent = new EventEmitter<Team>();
  @Output() deleteTeamEvent = new EventEmitter<number>();

  isEditing : boolean = false;

  newTeam: Team = {
    maximumBudget: 1
  };

  createTeam() {
    if(!this.isEditing){
      if (this.newTeam.name && this.newTeam.maximumBudget) {
        this.createTeamEvent.emit(this.newTeam);
        this.newTeam = {};
      }
    } else{
      this.onSaveEditedTeam();
    }
  }

  onEditTeam(team: Team) {
    this.newTeam = { ...team };
    this.isEditing = true;
  }

  onSaveEditedTeam() {
    if (this.newTeam.name && this.newTeam.maximumBudget) {
      this.editTeamEvent.emit(this.newTeam);
      this.newTeam = {};
      this.isEditing = false;
    }
  }

  onCancelEditTeam() {
    this.newTeam = {};
    this.isEditing = false;
  }

  onDeleteTeam(teamId: number) {
    if(confirm("Are you sure you want to delete this team?")){
      this.deleteTeamEvent.emit(teamId);
    }
  }

  get maxBidStatus(): string {
    if (this.newTeam.maximumBudget == 1) {
      return "1";
    } else if (this.newTeam.maximumBudget && this.newTeam.maximumBudget < 1000) {
      return 'Too Low';
    } else if (this.newTeam.maximumBudget && this.newTeam.maximumBudget < 5000) {
      return 'Low';
    } else {
      return 'Good Budget';
    }
  }

}

