import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { Persona } from '../models/persona';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {


  constructor(private http:HttpClient) {}

  public getAllPersonas(): Observable<Array<Persona>> {
    return this.http.get<Array<Persona>>(`${environment.backendHost}/api/personas/findAllPersonas`);
  }

  
  public savePersona(persona: Persona): Observable<Persona> {
  return this.http.post<Persona>(`${environment.backendHost}/api/personas/savePersonas`, persona);
}

public deletePersona(id: number): Observable<void> {
  return this.http.delete<void>(`${environment.backendHost}/api/personas/deletePersonas/${id}`);
}


}
