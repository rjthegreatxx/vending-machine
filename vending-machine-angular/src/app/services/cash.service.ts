import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cash } from '../models/cash.model';
import { Totalcashdto } from '../models/totalcashdto.model';

const baseUrl = 'http://localhost:8080/api/cash';

@Injectable({
  providedIn: 'root'
})
export class CashService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Cash[]> {
    return this.http.get<Cash[]>(baseUrl);
  }

  getTotalCashDto(): Observable<Totalcashdto> {
    return this.http.get<Totalcashdto>(`${baseUrl}/total`);
  }

  dispenseQuarters(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/getcash`, data);
  }

}