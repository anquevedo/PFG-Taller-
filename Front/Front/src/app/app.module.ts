import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaCocheComponent } from './coche/lista-coche.component';
import { DetalleCocheComponent } from './coche/detalle-coche.component';
import { NuevoCocheComponent } from './coche/nuevo-coche.component';
import { EditarCocheComponent } from './coche/editar-coche.component';
import { interceptorProvider } from './interceptors/coche-interceptor.service';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// external
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { MenuComponent } from './menu/menu.component';
import { IndexComponent } from './index/index.component';
import { SendEmailComponent } from './changepassword/send-email.component';
import { ChangePasswordComponent } from './changepassword/change-password.component';
import { ListaMecanicoComponent } from './Mecanico/lista-mecanico/lista-mecanico.component';
import { NuevoMecanicoComponent } from './Mecanico/nuevo-mecanico/nuevo-mecanico.component';
import { ListaIncidenciaComponent } from './incidencia/lista-incidencia.component';
import { EditarIncidenciaComponent } from './incidencia/editar-incidencia.component';
import { NuevaIncidenciaComponent } from './incidencia/nueva-incidencia.component';



@NgModule({
  declarations: [
    AppComponent,
    ListaCocheComponent,
    DetalleCocheComponent,
    NuevoCocheComponent,
    EditarCocheComponent,
    LoginComponent,
    RegistroComponent,
    MenuComponent,
    IndexComponent,
    SendEmailComponent,
    ChangePasswordComponent,
    ListaMecanicoComponent,
    NuevoMecanicoComponent,
    ListaIncidenciaComponent,
    EditarIncidenciaComponent,
    NuevaIncidenciaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
