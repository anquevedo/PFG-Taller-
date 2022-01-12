import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Coche } from '../models/coche';

@Injectable({
  providedIn: 'root'
})
export class CocheService {

  cocheURL= 'http://localhost:8080/Coche/'

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Coche[]> {
    return this.httpClient.get<Coche[]>(this.cocheURL + 'lista');
  }

  public detail(id: number): Observable<Coche> {
    return this.httpClient.get<Coche>(this.cocheURL + `detail/${id}`);
  }

  public detailName(matricula: string): Observable<Coche> {
    return this.httpClient.get<Coche>(this.cocheURL + `detailname/${matricula}`);
  }

  public save(coche: Coche): Observable<any> {
    return this.httpClient.post<any>(this.cocheURL + 'create', coche);
  }

  public update(id: number, coche: Coche): Observable<any> {
    return this.httpClient.put<any>(this.cocheURL + `update/${id}`, coche);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.cocheURL + `delete/${id}`);
  }
}
