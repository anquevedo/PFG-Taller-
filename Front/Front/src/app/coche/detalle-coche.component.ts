import { Component, OnInit } from '@angular/core';
import {CocheService } from '../service/coche.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Coche } from '../models/coche';

@Component({
  selector: 'app-detalle-coche',
  templateUrl: './detalle-coche.component.html',
  styleUrls: ['./detalle-coche.component.css']
})
export class DetalleCocheComponent implements OnInit {

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
        this.volver();
      }
    );
  }

  volver(): void {
    this.router.navigate(['/lista']);
  }

}
