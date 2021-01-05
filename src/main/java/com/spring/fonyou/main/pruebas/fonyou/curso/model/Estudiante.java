package com.spring.fonyou.main.pruebas.fonyou.curso.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Estudiante implements Serializable{

	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private String sexo;
	private String edad;
	private String correo;

	public Estudiante(String nombre, String apPaterno, String apMaterno, String sexo, String edad, String correo) {
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.sexo = sexo;
		this.edad = edad;
		this.correo = correo;
	}
	
}
