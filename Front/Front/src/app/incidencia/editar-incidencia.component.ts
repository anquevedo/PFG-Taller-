import { Component, OnInit } from '@angular/core';
import { Incidencia } from '../models/incidencia';
import { IncidenciaService } from '../service/incidencia.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-editar-incidencia',
  templateUrl: './editar-incidencia.component.html',
  styleUrls: ['./editar-incidencia.component.css']
})
export class EditarIncidenciaComponent implements OnInit {

  incidencia!: Incidencia;


  constructor(
    private incidenciaService: IncidenciaService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.incidenciaService.detail(id).subscribe(
      data => {
        this.incidencia = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.incidenciaService.updateEstado(id, this.incidencia).subscribe(
      data => {
        this.toastr.success('Coche Actualizado', 'OK', {
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
}
