import { Component, OnInit } from '@angular/core';
import { Incidencia } from '../models/incidencia';
import { IncidenciaService } from '../service/incidencia.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-lista-incidencia',
  templateUrl: './lista-incidencia.component.html',
  styleUrls: ['./lista-incidencia.component.css']
})
export class ListaIncidenciaComponent implements OnInit {

  incidencia: Incidencia[] = [];
  isAdmin=false;
  isMecanico = false;
  isUser=false;

  constructor(
    private incidenciaService: IncidenciaService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.cargarIncidencias();
    this.isAdmin=this.tokenService.isAdmin();
    this.isMecanico = this.tokenService.isMecanico();
    this.isUser = this.tokenService.isUser();
  }

  cargarIncidencias(): void {
    this.incidenciaService.lista().subscribe(
      data => {
        this.incidencia = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(id: number) {
    this.incidenciaService.delete(id).subscribe(
      data => {
        this.toastr.success('Incidencia Eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarIncidencias();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

}
