import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private readonly API_URL = "http://localhost:8080/users"

  constructor(private http: HttpClient) { }

  getUsers(): Observable<any>{
    return this.http.get<any>(`${this.API_URL}`)
  }

  getAllCustomers(): Observable<any>{
    return this.http.get<any>(`${this.API_URL}/customers`)
  }

  getCustomerById(id: any): Observable<any>{
    return this.http.get<any>(`${this.API_URL}/${id}`)
  }

  postLogin(body: any): Observable<any>{
    return this.http.post<any>(`${this.API_URL}/login`, body)
  }
  postRegister(body: any): Observable<any>{
    return this.http.post<any>(`${this.API_URL}/customer`, body)
  }
  upldateProfile(id: any, body: any): Observable<any>{
    return this.http.put<any>(`${this.API_URL}/customer/${id}`, body)
  }
}
