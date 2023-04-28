import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Soda } from '../models/soda.model';
import { Returnsodadto } from '../models/returnsodadto.model';

const baseUrl = 'http://localhost:8080/api/sodas';
const baseVendingmachineUrl = 'http://localhost:8080/api/vendingmachine';

@Injectable({
  providedIn: 'root'
})
export class VendingmachineService {

  constructor(private http: HttpClient) { }

  get(id: any): Observable<Soda> {
    return this.http.get<Soda>(`${baseUrl}/${id}`);
  }

  purchaseSoda(data: any): Observable<any> {
    //debugger;
    return this.http.post(`${baseVendingmachineUrl}/purchase`, data);
  }
}