package com.spring.fonyou.main.pruebas.fonyou.curso.repository;

import java.util.Map;
import com.spring.fonyou.main.pruebas.fonyou.curso.model.Estudiante;

public interface RedisRepository {
//	Buscar a todos los estudiantes
	Map<Object, Estudiante> todos();
//	Buscar por id al estudiante
	Estudiante porId(String id);
//	Guardar a el estudiante nuevo
	void guardar(Estudiante id);
//	Eliminar al estudiante
	void borrar(String id);
}
