import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiCallingService } from '../../services/api-calling.service';
import { PointCardComponent } from '../../components/point-card/point-card.component';

@Component({
  selector: 'app-point-table',
  standalone: true,
  imports: [CommonModule,PointCardComponent],
  templateUrl: './point-table.component.html',
  styleUrl: './point-table.component.css'
})
export class PointTableComponent implements OnInit{
  allPoints:any
  constructor(private _api:ApiCallingService){

  }
  ngOnInit(): void {
    this.loadPointTable();
  }
  loadPointTable() {
    this._api.getPointTable().subscribe({
      next:data=>{
        this.allPoints = data;
        console.log("All points : "+ this.allPoints+" end ")      
      },
      error:error=>{
        console.log(error);
      }
    });
    
  }
}
