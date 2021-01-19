package com.juegosdemesa.tinyboardgames.repositorios.Daos;

import com.juegosdemesa.tinyboardgames.entidades.Rol;
import com.juegosdemesa.tinyboardgames.entidades.Usuario;

public interface DaoUsuario extends Dao<Usuario> {
    Rol obtenerRolUsuario(Long rolUsuario);

    Usuario obtenerUsuarioPorId(Long id);
}
