import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaCocheComponent } from './coche/lista-coche.component';
import { DetalleCocheComponent } from './coche/detalle-coche.component';
import { NuevoCocheComponent } from './coche/nuevo-coche.component';
import { EditarCocheComponent } from './coche/editar-coche.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { CocheGuardService } from './guards/coche-guard.service';
import { ChangePasswordComponent } from './changepassword/change-password.component';
import { SendEmailComponent } from './changepassword/send-email.component';
import { LoginGuard } from './guards/login.guards';
import { ListaMecanicoComponent } from './Mecanico/lista-mecanico/lista-mecanico.component';
import { MecanicoGuardService } from './guards/mecanico-guard.service';
import { NuevoMecanicoComponent } from './Mecanico/nuevo-mecanico/nuevo-mecanico.component';


const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'registro', component: RegistroComponent, canActivate: [LoginGuard] },
  { path: 'sendemail', component: SendEmailComponent, canActivate: [LoginGuard] },
  { path: 'change-password/:tokenPassword', component: ChangePasswordComponent, canActivate: [LoginGuard] },
  { path: 'lista', component: ListaCocheComponent, canActivate: [CocheGuardService], data: { expectedRol: ['admin', 'user'] } },
  { path: 'detalle/:id', component: DetalleCocheComponent, canActivate: [CocheGuardService], data: { expectedRol: ['admin', 'user'] } },
  { path: 'nuevo', component: NuevoCocheComponent, canActivate: [CocheGuardService], data: { expectedRol: ['admin'] } },
  { path: 'editar/:id', component: EditarCocheComponent, canActivate: [CocheGuardService], data: { expectedRol: ['admin'] } },
  { path: 'nuevoMecanico', component: NuevoMecanicoComponent, canActivate: [MecanicoGuardService],data: { expectedRol: ['admin', 'user'] } },
  { path: 'listaMecanico', component: ListaMecanicoComponent, canActivate: [MecanicoGuardService],data: { expectedRol: ['admin', 'user'] } },

  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }