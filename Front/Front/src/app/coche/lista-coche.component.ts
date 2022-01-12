import { Component, OnInit } from '@angular/core';
import { Coche } from '../models/coche';
import { CocheService } from '../service/coche.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-lista-coche',
  templateUrl: './lista-coche.component.html',
  styleUrls: ['./lista-coche.component.css']
})
export class ListaCocheComponent implements OnInit {

  coches: Coche[] = [];
  roles!: string[];
  isAdmin = false;

  constructor(
    private cocheService: CocheService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.cargarCoches();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
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