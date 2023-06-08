import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FilestorageService {
  private readonly API_URL = "http://localhost:8080"
  constructor(private http: HttpClient) { }

  getFile(): Observable<any>{
    return this.http.get<any>(`${this.API_URL}/files/profile.img`)
  }

  postFile(body: any): Observable<any>{
    return this.http.post<any>(`${this.API_URL}/upload`, body)
  }
}
