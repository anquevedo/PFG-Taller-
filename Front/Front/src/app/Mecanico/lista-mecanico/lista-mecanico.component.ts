import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoUsuario } from 'src/app/models/nuevo-usuario';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-lista-mecanico',
  templateUrl: './lista-mecanico.component.html',
  styleUrls: ['./lista-mecanico.component.css']
})
export class ListaMecanicoComponent implements OnInit {

  isAdmin = false;
  nuevoUsuario: NuevoUsuario[] = [];

  constructor(
    private authService: AuthService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }


  

  ngOnInit() {
    this.cargarCoches();
    this.isAdmin = this.tokenService.isAdmin();
  }

  cargarCoches(): void {
    this.authService.lista().subscribe(
      data => {
        this.nuevoUsuario = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(id: string) {
    this.authService.delete(id).subscribe(
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
