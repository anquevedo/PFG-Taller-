import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoUsuario } from 'src/app/models/nuevo-usuario';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.css']
})
export class ListaUsuariosComponent implements OnInit {

  isAdmin = false;
  nuevoUsuario: NuevoUsuario[] = [];

  constructor(
    private authService: AuthService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }



  ngOnInit() {
    this.cargarUsuario();
    this.isAdmin = this.tokenService.isAdmin();
  }

  cargarUsuario(): void {
    this.authService.listaUsuarios().subscribe(
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
        this.toastr.success('Usuario Eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarUsuario();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );

}
}
