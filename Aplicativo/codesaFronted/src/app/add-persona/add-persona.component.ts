import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PersonaService } from '../services/persona.service';
import { Router } from '@angular/router';
import { Persona } from '../models/persona';

@Component({
  selector: 'app-add-persona',
  standalone: false,
  templateUrl: './add-persona.component.html',
  styleUrl: './add-persona.component.css'
})

export class AddPersonaComponent implements OnInit {
  personaForm: FormGroup;

  personas: Persona[] = [];

  constructor(
    private fb: FormBuilder,
    private personaService: PersonaService,
    private router: Router
  ) {
    this.personaForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.maxLength(60)]],
      apellido: ['', [Validators.required, Validators.maxLength(60)]],
      fecha_nacimiento: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
    });
  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  agregarPersona(): void {
    if (this.personaForm.valid) {
        this.personaService.savePersona(this.personaForm.value).subscribe({
            next: (personaGuardada) => {
                console.log('Persona guardada con éxito:', personaGuardada);
                alert('Persona agregada con éxito');
                //this.getPersonas(); // Implementa getPersonas si es necesario
                this.personaForm.reset(); // Limpia el formulario
                this.router.navigate(['/personas'])
            },
            error: (error) => {
                console.error('Error al agregar persona:', error);
                alert('Error al agregar persona');
            }
        });
    }
}
}
