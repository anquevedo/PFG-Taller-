import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Incidencia } from '../models/incidencia';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IncidenciaService {

  incidenciaURL= environment.incidenciaURL;
  
  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Incidencia[]> {
    return this.httpClient.get<Incidencia[]>(this.incidenciaURL + 'lista');
  }

  
  public listaPorNombreUsuario(nombreUsuario: string): Observable<Incidencia[]> {
    return this.httpClient.get<Incidencia[]>(this.incidenciaURL + `listaPorNombreUsuario/${nombreUsuario}`);
  }

  public listaPorNombreMecanico(nombreUsuario: string): Observable<Incidencia[]> {
    return this.httpClient.get<Incidencia[]>(this.incidenciaURL + `listaPorNombreMecanico/${nombreUsuario}`);
  }

  public listaSinSeleccionar(): Observable<Incidencia[]> {
    return this.httpClient.get<Incidencia[]>(this.incidenciaURL + `listaSinSeleccionar/`);
  }
  
  public detail(id: number): Observable<Incidencia> {
    return this.httpClient.get<Incidencia>(this.incidenciaURL + `detail/${id}`);
  }

  public save(incidencia: Incidencia): Observable<any> {
    return this.httpClient.post<any>(this.incidenciaURL + 'create', incidencia);
  }

  public updateEstado(id: number, incidencia: Incidencia): Observable<any> {
    return this.httpClient.put<any>(this.incidenciaURL + `updateEstado/${id}`, incidencia);
  }


  public seleccionarIncidencia(id: number, nombreUsuario: string): Observable<any> {
    let params = new HttpParams();

    params = params.append('id', id.toString());
    params = params.append('nombreUsuario', nombreUsuario);
    
    return this.httpClient.put<any>(this.incidenciaURL + `seleccionarIncidencia/`+ id +'/' + nombreUsuario,{params: params });
  }

  public quitarIncidencia(id: number, nombreUsuario: string): Observable<any> {
    let params = new HttpParams();

    params = params.append('id', id.toString());
    params = params.append('nombreUsuario', nombreUsuario);
    
    return this.httpClient.put<any>(this.incidenciaURL + `quitarIncidencia/`+ id +'/' + nombreUsuario,{params: params });
  }

  

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.incidenciaURL + `delete/${id}`);
  }
}
