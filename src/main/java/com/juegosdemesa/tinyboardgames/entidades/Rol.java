package com.juegosdemesa.tinyboardgames.entidades;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {

    @Max(10)
    private Long id;
    @Size(min = 3, max = 150)
    private String nombre, descripcion;
}
