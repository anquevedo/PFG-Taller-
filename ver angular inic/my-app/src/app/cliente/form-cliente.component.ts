import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';

@Component({
  selector: 'app-from-cliente',
  templateUrl: './form-cliente.component.html',
  styleUrls: ['./form-cliente.component.css']
})
export class FormClienteComponent implements OnInit {
  cliente:Cliente = new Cliente();
  titulo:String="Registro Cliente";

  constructor(private clienteService: ClienteService, private router:Router) { }

  ngOnInit(): void {
  }

  create(): void{
    console.log(this.cliente);
    this.clienteService.create(this.cliente).subscribe(
      res=>this.router.navigate(['/cliente'])
    );
  }
}
