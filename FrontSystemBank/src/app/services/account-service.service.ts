import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {
  private readonly API_URL = "http://localhost:8080/accounts"

  constructor(private http: HttpClient) { }

  getAccountByCostumer(id: any): Observable<any>{
    return this.http.get<any>(`${this.API_URL}/customer/${id}`)
  }
}
