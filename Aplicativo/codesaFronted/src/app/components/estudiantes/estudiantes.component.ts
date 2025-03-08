import { Component, OnInit } from '@angular/core';
import { Estudiante } from '../../models/estudiante';
import { EstudianteService } from '../../services/estudiante.service';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';

@Component({
  selector: 'app-estudiantes',
  standalone: false,
  templateUrl: './estudiantes.component.html',
  styleUrl: './estudiantes.component.css'
})
export class EstudiantesComponent implements OnInit {

  estudiantes: any;
  estudiantesDataSource!: MatTableDataSource<Estudiante>;
  displayedColumns = ['id_persona', 'numero_matricula', 'grado'];
  
  constructor(private estudianteService: EstudianteService, private router: Router) { }

  ngOnInit(): void {
    this.estudianteService.getAllEstudiantes().subscribe({
      next: value => {
        this.estudiantes = value;
        this.estudiantesDataSource = new MatTableDataSource(this.estudiantes);
        
      },
      error : (err) => {
        console.log(err);
      }
    });
  }

  listarEstudiantesById(estuadiante : Estudiante) {
    this.router.navigate([`/estudiante/${estuadiante.id_persona}`]);
  }

}
