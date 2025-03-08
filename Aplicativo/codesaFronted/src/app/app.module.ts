import { NgModule } from '@angular/core';
import {
  BrowserModule,
  provideClientHydration,
  withEventReplay,
} from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AdminTemplateComponent } from './components/admin-template/admin-template.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { PersonasComponent } from './components/personas/personas.component';
import { EstudiantesComponent } from './components/estudiantes/estudiantes.component';
import { ProfesorComponent } from './components/profesor/profesor.component';
import { AdministrativoComponent } from './components/administrativo/administrativo.component';
import { CursoComponent } from './components/curso/curso.component';
import { InscripcionComponent } from './components/inscripcion/inscripcion.component';
import { HomeComponent } from './components/home/home.component';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import {HttpClientModule} from '@angular/common/http';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import { EstudianteDetailsComponent } from './estudiante-details/estudiante-details.component';
import { AddPersonaComponent } from './add-persona/add-persona.component';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminTemplateComponent,
    PersonasComponent,
    EstudiantesComponent,
    ProfesorComponent,
    AdministrativoComponent,
    CursoComponent,
    InscripcionComponent,
    HomeComponent,
    EstudianteDetailsComponent,
    AddPersonaComponent,
    ConfirmDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatTableModule,
    HttpClientModule,
    MatPaginatorModule,
    MatSortModule,

  ],
  providers: [provideClientHydration(withEventReplay())],
  bootstrap: [AppComponent],
})
export class AppModule {}
