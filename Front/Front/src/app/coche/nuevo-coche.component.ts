import { Component, OnInit } from '@angular/core';
import { CocheService } from '../service/coche.service';
import { Coche } from '../models/coche';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { TokenService } from '../service/token.service';

const TOKEN_KEY = 'AuthToken';

@Component({
  selector: 'app-nuevo-coche',
  templateUrl: './nuevo-coche.component.html',
  styleUrls: ['./nuevo-coche.component.css']
})
export class NuevoCocheComponent implements OnInit {

  precio!: number;
  matricula!: string;
  marca!: string;
  modelo!: string;
  anio!: number;
  nombreUsuario!: string;
  errMsj!: string;

  constructor(
    private cocheService: CocheService,
    private toastr: ToastrService,
    private router: Router,
    private tokenService: TokenService
    ) { }

  ngOnInit() {
  }

  onCreate(): void {
    this.nombreUsuario = this.tokenService.getUserName();
    const coche = new Coche(this.matricula, this.marca, this.modelo, this.anio, this.precio, this.nombreUsuario);
    this.cocheService.save(coche).subscribe(
      data => {
        if(data.mensaje ==='esa matricula ya existe'){
          this.toastr.error('La matricula ya existe', 'Fail', {
            timeOut: 3000, positionClass: 'toast-top-center'
          });
        }
        else this.toastr.success('Coche Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/lista']);
      },
      err => {
        this.errMsj = err.error.mensaje;
        this.toastr.error(this.errMsj, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }

  public getUserName(): string {
    if (!this.isLogged()) {
      return null!;
    }
    const token = this.getToken();
    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const username = values.sub;
    return username;
  }

  public getToken(): string {
    return localStorage.getItem(TOKEN_KEY)!;
  }

  public isLogged(): boolean {
    if (this.getToken()) {
      return true;
    }
    return false;
  }
}
