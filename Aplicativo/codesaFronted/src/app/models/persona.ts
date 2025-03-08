import { Estudiante } from "./estudiante";

export interface Persona {
    id_persona?: number;
    nombre: string;
    apellido: string;
    fecha_nacimiento: Date;
    email: string;
    telefono: string;
}
