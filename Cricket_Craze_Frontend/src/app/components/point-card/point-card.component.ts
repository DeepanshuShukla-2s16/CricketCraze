import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-point-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './point-card.component.html',
  styleUrl: './point-card.component.css'
})
export class PointCardComponent {
@Input() pntTblRow:any
}
