import { Component, OnInit } from '@angular/core';
import { IncidenciaService } from '../service/incidencia.service';
import { Incidencia } from '../models/incidencia';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { TokenService } from '../service/token.service';

const TOKEN_KEY = 'AuthToken';

@Component({
  selector: 'app-nueva-incidencia',
  templateUrl: './nueva-incidencia.component.html',
  styleUrls: ['./nueva-incidencia.component.css']
})


export class NuevaIncidenciaComponent implements OnInit {

  id!: number;
  descripcion!: string;
  dateInicio!: Date;
  dateFin!: Date;
  estado!: string;
  nombreUsuario!: string;


  constructor(
    private incidenciaService: IncidenciaService,
    private toastr: ToastrService,
    private router: Router,
    private tokenService: TokenService

    ) { }

  ngOnInit(): void {
  }

  onCreate(): void {
    this.nombreUsuario = this.tokenService.getUserName();
    const coche = new Incidencia(this.descripcion, this.dateInicio, this.dateFin, this.estado, this.nombreUsuario);
    this.incidenciaService.save(coche).subscribe(
      data => {
        this.toastr.success('Incidencia Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/listaincidencia']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
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
