package com.spring.fonyou.main.pruebas.fonyou.curso.repository;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.spring.fonyou.main.pruebas.fonyou.curso.model.Estudiante;

@Repository
public class EstudianteRepository implements RedisRepository {

	private static final String KEY = "Estudiantes";
	
	private RedisTemplate<String, Estudiante> redisTempl;
//	Ejecutas las operaciones por medio del redisTempl
	private HashOperations hashOperacionesBD;

	public EstudianteRepository(RedisTemplate<String, Estudiante> redisTempl) {
		this.redisTempl = redisTempl;
	}

//	Seteamos el init de HasOperations
	@PostConstruct
	private void init() {
		hashOperacionesBD = redisTempl.opsForHash();
	}

	
	@Override
	public Map<String, Estudiante> todos() {
		return hashOperacionesBD.entries(KEY);
	}

	@Override
	public Estudiante porId(String id) {
		// Se caste a Tipo: Estudiante (Clase)
		return (Estudiante) hashOperacionesBD.get(KEY,id);
	}

	@Override
	public void guardar(Estudiante estud) {
//		Con esto ==> UUID.randomUUID().toString() se crea un ID aleatorio
//		System.out.println("ANTES DE GUARDAR");
		hashOperacionesBD.put(KEY, ""+UUID.randomUUID(), estud);
	}

	@Override
	public void borrar(String id) {
		hashOperacionesBD.delete(KEY, id);
	}

}
