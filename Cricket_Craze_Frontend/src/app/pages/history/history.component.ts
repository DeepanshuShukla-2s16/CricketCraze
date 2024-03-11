import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiCallingService } from '../../services/api-calling.service';
import { MatchCardComponent } from '../../components/match-card/match-card.component';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule,MatchCardComponent],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent implements OnInit {
  allMatches: any;
  constructor(private _api:ApiCallingService){

  }
  ngOnInit(): void {
    this.loadAllMatches();
  }
  loadAllMatches() {
    this._api.getAllMatches().subscribe({
      next:data=>{
        this.allMatches = data;
      },
      error:error=>{
        console.log(error);
        
      }
    })
  }
}
