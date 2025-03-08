import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Estudiante } from '../models/estudiante';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstudianteService {


  constructor(private http:HttpClient) {}

  public getAllEstudiantes(): Observable<Array<Estudiante>> {
    return this.http.get<Array<Estudiante>>(`${environment.backendHost}/api/estudiantes/findAllEstudiantes`);
  }

  public getEstudianteById(id: number): Observable<Estudiante> {
    return this.http.get<Estudiante>(`${environment.backendHost}/api/estudiantes/findEstudianteById/${id}`);
  } 
  

  
}
