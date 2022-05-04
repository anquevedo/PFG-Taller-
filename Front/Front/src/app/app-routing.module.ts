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
import { ListaIncidenciaComponent } from './incidencia/lista-incidencia.component';
import { EditarIncidenciaComponent } from './incidencia/editar-incidencia.component';
import { NuevaIncidenciaComponent } from './incidencia/nueva-incidencia.component';
import { IncidenciaGuardService } from './guards/incidencia-guard.service';
import { ListaUsuariosComponent } from './auth/lista-usuarios.component';
import { UsuarioGuardService } from './guards/usuario-guard.service';
import { MisIncidenciaComponent } from './incidencia/mis-incidencia.component';





const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'registro', component: RegistroComponent, canActivate: [LoginGuard] },
  { path: 'sendemail', component: SendEmailComponent, canActivate: [LoginGuard] },
  { path: 'change-password/:tokenPassword', component: ChangePasswordComponent, canActivate: [LoginGuard] },
  { path: 'lista', component: ListaCocheComponent, canActivate: [CocheGuardService], data: { expectedRol: ['admin', 'user', 'mecanico'] } },
  { path: 'detalle/:id', component: DetalleCocheComponent, canActivate: [CocheGuardService], data: { expectedRol: ['admin', 'user', 'mecanico'] } },
  { path: 'nuevo', component: NuevoCocheComponent, canActivate: [CocheGuardService], data: { expectedRol: ['admin', 'user', 'mecanico'] } },
  { path: 'editar/:id', component: EditarCocheComponent, canActivate: [CocheGuardService], data: { expectedRol: ['admin', 'user'] } },
  
  { path: 'nuevoMecanico', component: NuevoMecanicoComponent, canActivate: [MecanicoGuardService],data: { expectedRol: ['admin', 'user'] } },
  { path: 'listaMecanico', component: ListaMecanicoComponent, canActivate: [MecanicoGuardService],data: { expectedRol: ['admin', 'user'] } },
  { path: 'listaUsuario', component: ListaUsuariosComponent, canActivate: [UsuarioGuardService],data: { expectedRol: ['admin', 'user'] } },

  { path: 'misIncidencia', component: MisIncidenciaComponent, canActivate: [IncidenciaGuardService],data: { expectedRol: ['mecanico', 'admin', 'user'] } },
  { path: 'nuevaIncidencia', component: NuevaIncidenciaComponent, canActivate: [IncidenciaGuardService],data: { expectedRol: ['mecanico', 'admin', 'user'] } },
  { path: 'listaincidencia', component: ListaIncidenciaComponent, canActivate: [IncidenciaGuardService],data: { expectedRol: ['mecanico', 'admin', 'user'] } },
  { path: 'editarIncidencia/:id', component: EditarIncidenciaComponent, canActivate: [IncidenciaGuardService], data: { expectedRol: ['mecanico', 'admin', 'user'] } },



  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }