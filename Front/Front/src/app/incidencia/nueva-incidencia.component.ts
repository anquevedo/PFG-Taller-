import { Component, OnInit } from '@angular/core';
import { IncidenciaService } from '../service/incidencia.service';
import { Incidencia } from '../models/incidencia';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
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

  constructor(
    private incidenciaService: IncidenciaService,
    private toastr: ToastrService,
    private router: Router
    ) { }

  ngOnInit(): void {
  }

  onCreate(): void {
    const coche = new Incidencia(this.descripcion, this.dateInicio, this.dateFin, this.estado);
    this.incidenciaService.save(coche).subscribe(
      data => {
        this.toastr.success('Incidencia Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/lista']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }
}
