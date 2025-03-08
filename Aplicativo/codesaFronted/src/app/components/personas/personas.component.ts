import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PersonaService } from '../../services/persona.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-personas',
  standalone: false,
  templateUrl: './personas.component.html',
  styleUrl: './personas.component.css'
})
export class PersonasComponent implements OnInit {

  public personas: any;
  public dataSource: any;
  public displayedColumns = ['id_persona', 'nombre', 'apellido', 'fecha_nacimiento', 'email', 'telefono', 'eliminar'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private http: HttpClient, private personaService: PersonaService, private router: Router) { }

  ngOnInit(): void {

    this.personaService.getAllPersonas().subscribe({
      next: data => {
        this.personas = data;
        this.dataSource = new MatTableDataSource(this.personas);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  eliminar(id_persona: number): void {
    this.personaService.deletePersona(id_persona).subscribe({
      next: () => {
        this.personaService.getAllPersonas().subscribe({
          next: data => {
            this.personas = data;
            this.dataSource = new MatTableDataSource(this.personas);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sort;
          },
          error: (error) => {
            console.log(error);
          }
        })
      },
      error: (error) => {
        console.log(error);
      }
    })
  }


}
