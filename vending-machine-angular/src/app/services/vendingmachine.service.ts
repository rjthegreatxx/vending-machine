import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cash } from '../models/cash.model';
import { Purchasesodadto } from '../models/purchasesodadto.model';
import { Returnsodadto } from '../models/returnsodadto.model';
import { Soda } from '../models/soda.model';

const baseUrl = 'http://localhost:8080/api/cash';

@Injectable({
  providedIn: 'root'
})
export class VendingmachineService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Soda[]> {
    return this.http.get<Soda[]>(baseUrl);
  }

  get(id: any): Observable<Soda> {
    return this.http.get<Soda>(`${baseUrl}/${id}`);
  }

}