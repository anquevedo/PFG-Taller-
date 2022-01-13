import { Component, OnInit } from '@angular/core';
import { Coche } from '../models/coche';
import { CocheService } from '../service/coche.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-editar-coche',
  templateUrl: './editar-coche.component.html',
  styleUrls: ['./editar-coche.component.css']
})
export class EditarCocheComponent implements OnInit {

  coche!: Coche;

  constructor(
    private cocheService: CocheService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.cocheService.detail(id).subscribe(
      data => {
        this.coche = data;
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
    this.cocheService.update(id, this.coche).subscribe(
      data => {
        this.toastr.success('Coche Actualizado', 'OK', {
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
