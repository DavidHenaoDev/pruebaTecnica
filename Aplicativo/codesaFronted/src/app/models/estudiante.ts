import { Persona } from "./persona";

export interface Estudiante {

    id_persona?: number;
    numero_matricula?: number;
    grado: string;
    
    persona?: Persona;
}
