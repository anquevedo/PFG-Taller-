import { Component, OnInit } from '@angular/core';
import { Coche } from '../models/coche';
import { CocheService } from '../service/coche.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';

const TOKEN_KEY = 'AuthToken';

@Component({
  selector: 'app-lista-coche',
  templateUrl: './lista-coche.component.html',
  styleUrls: ['./lista-coche.component.css']
})
export class ListaCocheComponent implements OnInit {

  coches: Coche[] = [];
  isAdmin = false;
  isUser=false;
  isMecanico=false;

  constructor(
    private cocheService: CocheService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    if(this.tokenService.isAdmin()){
      this.cargarCoches();
    }else this.cargarPorUsuario();
    this.isAdmin = this.tokenService.isAdmin();
    this.isUser = this.tokenService.isUser();
    this.isMecanico = this.tokenService.isMecanico();
  }

  cargarCoches(): void {
    this.cocheService.lista().subscribe(
      data => {
        this.coches = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  cargarPorUsuario(): void {
    this.cocheService.listaPorNombre(this.tokenService.getUserName()).subscribe(
      data => {
        this.coches = data;
      },
      err => {
        console.log(err);
      }
    );
  }
  
  borrar(id: number) {
    this.cocheService.delete(id).subscribe(
      data => {
        this.toastr.success('Coche Eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarCoches();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }


}
