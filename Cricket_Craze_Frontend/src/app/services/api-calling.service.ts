import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiCallingService {

  constructor(private _httpClient:HttpClient) {  }

  getAllMatches(){
    return this._httpClient.get(`${environment.apiUrl}/cricket/history`)
  }
  getLiveMatches(){
    return this._httpClient.get(`${environment.apiUrl}/cricket/lives`)
  }
  getPointTable(): Observable<any>{
    return this._httpClient.get<any>(`${environment.apiUrl}/cricket/point-table`)
  }
}
