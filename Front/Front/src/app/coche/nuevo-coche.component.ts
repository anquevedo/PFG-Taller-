import { Component, OnInit } from '@angular/core';
import { CocheService } from '../service/coche.service';
import { Coche } from '../models/coche';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

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

  constructor(
    private cocheService: CocheService,
    private toastr: ToastrService,
    private router: Router
    ) { }

  ngOnInit() {
  }

  onCreate(): void {
    const coche = new Coche(this.matricula, this.marca, this.modelo, this.anio, this.precio);
    this.cocheService.save(coche).subscribe(
      data => {
        this.toastr.success('Coche Creado', 'OK', {
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
