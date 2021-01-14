package com.juegosdemesa.tinyboardgames.entidades;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    @Max(10)
    private Long id;
    @Size(min = 3, max = 150)
    private String nombre, apellidos, email, password;
    private Rol rol;
    @Min(2)
    private Integer edad;
    private LocalDate fechaRegistro;

    public Usuario(Long id, String nombre, String apellidos, String email, Rol rol, Integer edad,
			LocalDate fechaRegistro) {
		this.id = id;
		this.nombre= nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.rol = rol;
		this.edad = edad;
		this.fechaRegistro = fechaRegistro;
	}
}
