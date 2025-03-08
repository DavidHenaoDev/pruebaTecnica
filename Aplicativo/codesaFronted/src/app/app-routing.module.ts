import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonasComponent } from './components/personas/personas.component';
import { EstudiantesComponent } from './components/estudiantes/estudiantes.component';
import { ProfesorComponent } from './components/profesor/profesor.component';
import { AdministrativoComponent } from './components/administrativo/administrativo.component';
import { CursoComponent } from './components/curso/curso.component';
import { InscripcionComponent } from './components/inscripcion/inscripcion.component';
import { HomeComponent } from './components/home/home.component';
import { AdminTemplateComponent } from './components/admin-template/admin-template.component';
import { EstudianteDetailsComponent } from './estudiante-details/estudiante-details.component';
import { AddPersonaComponent } from './add-persona/add-persona.component';


const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "home", component: HomeComponent },
  { path: "personas", component: PersonasComponent },
  { path: "estudiantes", component: EstudiantesComponent },
  { path: "profesores", component: ProfesorComponent },
  { path: "administrativos", component: AdministrativoComponent },
  { path: "cursos", component: CursoComponent },
  { path: "inscripciones", component: InscripcionComponent },
  { path: "estuadiante-detalles/:id", component: EstudianteDetailsComponent },
  { path: "add-persona", component: AddPersonaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
