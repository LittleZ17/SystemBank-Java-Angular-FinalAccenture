import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransferServiceService {

  private readonly API_URL = "http://localhost:8080/transactions"


  constructor(private http: HttpClient) { }

  getAllTransfers(): Observable<any>{
    return this.http.get<any>(`${this.API_URL}`)
  }
  
  getTransferById(id: any): Observable<any>{
    return this.http.get<any>(`${this.API_URL}/${id}`)
  }

  postTransfer(body: any): Observable<any>{
    return this.http.post<any>(`${this.API_URL}/new-transaction`, body)
  }
}
