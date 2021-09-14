import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Cliente} from './cliente';
import { create } from 'domain';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private url: string="http://localhost:8080/api/cliente"

  constructor( private http:HttpClient ) { }

  //Obtener Clientes
  getAll():Observable<Cliente[]>{
    return this.http.get<Cliente[]>(this.url);

  }

  //Crear Cliente
  create(cliente:Cliente):Observable<Cliente>{
    return this.http.post<Cliente>(this.url, cliente);
  }
  
  //Obtener un Cliente
  get(dni:number):Observable<Cliente>{
    return this.http.get<Cliente>(this.url+'/'+dni);
  }

  //Actualizar Cliente
  update(cliente:Cliente):Observable<Cliente>{
    return this.http.put<Cliente>(this.url, cliente)
  }

  //Eliminar Cliente
  delete(dni:number):Observable<Cliente>{
    return this.http.delete<Cliente>(this.url+'/'+dni);
  }
}
