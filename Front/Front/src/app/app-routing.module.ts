import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaCocheComponent } from './coche/lista-coche.component';
import { DetalleCocheComponent } from './coche/detalle-coche.component';
import { NuevoCocheComponent } from './coche/nuevo-coche.component';
import { EditarCocheComponent } from './coche/editar-coche.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { CocheGuardService as guard } from './guards/coche-guard.service';


const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'lista', component: ListaCocheComponent, canActivate: [guard], data: { expectedRol: ['admin', 'usuario'] } },
  { path: 'detalle/:id', component: DetalleCocheComponent, canActivate: [guard], data: { expectedRol: ['admin', 'usuario'] } },
  { path: 'nuevo', component: NuevoCocheComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'editar/:id', component: EditarCocheComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }