import { Component, OnInit } from '@angular/core';
import { Cliente} from './cliente';
import { ClienteService } from './cliente.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  titulo:string="Lista de Clientes";
  prueba:string='Esto es una prueba de mi componente Clientes';

  cliente!: Cliente[];

  constructor(private clienteService:ClienteService) { }

  ngOnInit(): void {
    this.clienteService.getAll().subscribe(
      c => this.cliente=c
    );
  }

}
