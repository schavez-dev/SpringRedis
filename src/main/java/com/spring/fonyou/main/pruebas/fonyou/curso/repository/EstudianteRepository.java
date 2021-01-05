package com.spring.fonyou.main.pruebas.fonyou.curso.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.spring.fonyou.main.pruebas.fonyou.curso.model.Estudiante;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EstudianteRepository implements RedisRepository {

	private static final String KEY = "Estudiantes";

	private RedisTemplate<Object, Estudiante> redisTempl;
//	Ejecutas las operaciones por medio del redisTempl
	private HashOperations<Object, Object, Estudiante> hashOperacionesBD;

	public EstudianteRepository(RedisTemplate<Object, Estudiante> redisTempl) {
		this.redisTempl = redisTempl;
	}

//	Seteamos el init de HasOperations
	@PostConstruct
	private void init() {
		hashOperacionesBD = redisTempl.opsForHash();
	}

	@Override
	public Map<Object, Estudiante> todos() {
		return hashOperacionesBD.entries(KEY);
	}

	@Override
	public Estudiante porId(String id) {
		// Se caste a Tipo: Estudiante (Clase)
		return (Estudiante) hashOperacionesBD.get(KEY, id);
	}

	@Override
	public void guardar(Estudiante estud) {

		System.out.println("FUNCION DE GUARDAR");
//		redisTempl
//        .opsForList()
//        .leftPush(KEY, estud);
		Map<String, Object> map = new HashMap<>();
		map.put("nombre", estud.getNombre());
		map.put("apPaterno", estud.getApPaterno());
		map.put("apMaterno", estud.getApMaterno());
		map.put("sexo", estud.getSexo());
		map.put("edad", estud.getEdad());
		map.put("correo", estud.getCorreo());

		try {
			redisTempl.opsForHash().putAll(KEY, map);
			log.info("Ejecutado!");
			System.out.println(map.size());
		} catch (Exception e) {
			log.error("Error al guardar el estudiante");
			log.error(KEY, e);
		}

	}

	@Override
	public void borrar(String id) {
		hashOperacionesBD.delete(KEY, id);
	}
}