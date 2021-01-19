package com.juegosdemesa.tinyboardgames.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mecanica {
    private Long id;

    private String nombre, descripcion;
}
