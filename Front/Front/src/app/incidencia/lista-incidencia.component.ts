import { Component, OnInit } from '@angular/core';
import { Incidencia } from '../models/incidencia';
import { IncidenciaService } from '../service/incidencia.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';
import { Router } from '@angular/router';

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
    private tokenService: TokenService,
    private router: Router
  ) { }

  ngOnInit() {
    if(this.tokenService.isAdmin()){
      this.cargarIncidencias();
    }else if(this.tokenService.isUser()){
      this.cargarPorUsuario();
    }else this.cargarSinSeleccionar();
    
    this.isAdmin=this.tokenService.isAdmin();
    this.isUser = this.tokenService.isUser();
    this.isMecanico = this.tokenService.isMecanico();
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

  
  cargarPorUsuario(): void {
    this.incidenciaService.listaPorNombreUsuario(this.tokenService.getUserName()).subscribe(
      data => {
        this.incidencia = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  cargarSinSeleccionar(): void {
    this.incidenciaService.listaSinSeleccionar().subscribe(
      data => {
        this.incidencia = data;
      },
      err => {
        
        console.log(err);
        this.router.navigate(['/listaincidencia']);
      }
    );
  }
  seleccionarIncidencia(id: number){
      this.incidenciaService.seleccionarIncidencia(id, this.tokenService.getUserName()).subscribe(
        data => {
          this.toastr.success('Incidencia Asignada', 'OK', {
            timeOut: 3000, positionClass: 'toast-top-center'
          });
          this.cargarSinSeleccionar();
          this.router.navigate(['/listaincidencia']);
        },
        err => {
          this.toastr.error(err.error.mensaje, 'Fail', {
            timeOut: 3000,  positionClass: 'toast-top-center',
          });
          this.router.navigate(['/listaincidencia']);
        }
      );
  }

  quitarIncidencia(id: number){
    this.incidenciaService.quitarIncidencia(id, this.tokenService.getUserName()).subscribe(
      data => {
        this.toastr.success('Incidencia Desasignada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarPorUsuario();
        this.router.navigate(['/listaIncidencia']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/listaIncidencia']);
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
