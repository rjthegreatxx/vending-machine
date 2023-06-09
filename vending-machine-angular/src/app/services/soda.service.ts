import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Soda } from '../models/soda.model';

const baseUrl = 'http://localhost:8080/api/sodas';

@Injectable({
  providedIn: 'root'
})
export class SodaService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Soda[]> {
    return this.http.get<Soda[]>(baseUrl);
  }

  get(id: any): Observable<Soda> {
    return this.http.get<Soda>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByName(name: any): Observable<Soda[]> {
    return this.http.get<Soda[]>(`${baseUrl}?name=${name}`);
  }
}