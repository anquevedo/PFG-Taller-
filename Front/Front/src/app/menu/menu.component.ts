import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLogged = false;
  isAdmin = false;
  isUser=false;
  isMecanico =false;

  constructor(private tokenService: TokenService) { }

  ngOnInit() {
   this.isLogged = this.tokenService.isLogged();
   this.isAdmin = this.tokenService.isAdmin();
   this.isUser = this.tokenService.isUser();
   this.isMecanico = this.tokenService.isMecanico();
  }

  onLogOut(): void {
    this.tokenService.logOut();
  }

}
