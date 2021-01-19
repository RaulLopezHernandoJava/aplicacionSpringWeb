package com.juegosdemesa.tinyboardgames.entidades;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Juego {
    @Max(10)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 150)
    private String nombre, autor, editorial;
    @DecimalMin(value = "0.1", inclusive = true)
    private Mecanica mecanica;
    private Double precio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPublicacion;
    private Boolean active;


}
