package com.spring.fonyou.main.pruebas.fonyou.curso.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spring.fonyou.main.pruebas.fonyou.curso.model.Estudiante;
import com.spring.fonyou.main.pruebas.fonyou.curso.repository.EstudianteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EstudianteController {
	private EstudianteRepository estudRepo;


	public EstudianteController(EstudianteRepository estudRepo) {
		this.estudRepo = estudRepo;
	}
	
	@GetMapping("/Alumnos")
	public Map<Object, Estudiante> todoAlumnos(){
		return estudRepo.todos();
	}
	
	@GetMapping("/Alumno/{id}")
	public Estudiante porId(@PathVariable String id) {
		return estudRepo.porId(id);
	}
	
	@PostMapping("/Estudiantes")
	public void altaAlumno(@RequestBody Estudiante estud) {
		estudRepo.guardar(estud);
	}
	
	@GetMapping("/Estudiantes/{id}")
	public void bajaAlumno(@PathVariable String id) {
		estudRepo.borrar(id);
	}
}
