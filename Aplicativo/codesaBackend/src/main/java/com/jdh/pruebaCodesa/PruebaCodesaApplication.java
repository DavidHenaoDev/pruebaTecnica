package com.jdh.pruebaCodesa;

import com.jdh.pruebaCodesa.entity.Persona;
import com.jdh.pruebaCodesa.repository.EstudianteRepository;
import com.jdh.pruebaCodesa.repository.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;


@SpringBootApplication
public class PruebaCodesaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaCodesaApplication.class, args);
	}
	

	/*@Bean
	CommandLineRunner commandLineRunner(PersonaRepository personaRepository, EstudianteRepository estudianteRepository){
		return args -> {
			personaRepository.save(Persona.builder()
					.id_persona(null)
					.nombre("JuanPrueba")
					.apellido("HenaoJPrueba")
					.fecha_nacimiento(LocalDate.parse("2003-04-17"))
					.email("juanPrueba@example.com")
					.telefono("0000000000")
					.build());

			personaRepository.save(Persona.builder()
					.id_persona(null)
					.nombre("EstebanPrueba")
					.apellido("vallejoPrueba")
					.fecha_nacimiento(LocalDate.parse("2000-10-03"))
					.email("estebanPrueba@example.com")
					.telefono("1111111111")
					.build());

			personaRepository.save(Persona.builder()
					.id_persona(null)
					.nombre("DanielPrueba")
					.apellido("GomezPrueba")
					.fecha_nacimiento(LocalDate.parse("2004-04-04"))
					.email("danielPrueba@example.com")
					.telefono("2222222222")
					.build());

		};
	}*/
}