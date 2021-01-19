package com.juegosdemesa.tinyboardgames.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.juegosdemesa.tinyboardgames.entidades.Rol;
import com.juegosdemesa.tinyboardgames.entidades.Usuario;

import org.springframework.jdbc.core.RowMapper;

public class UsuarioRowMapper  implements RowMapper<Usuario>{
    
    @Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Rol rol;
		Usuario usuario;

		rol = new Rol();
		rol.setId(rs.getLong("r.id"));;
		rol.setNombre(rs.getString("r.nombre"));
		rol.setDescripcion(rs.getString("r.descripcion"));
		
        usuario = new Usuario();
        usuario.setId(rs.getLong("u.id"));;
        usuario.setNombre(rs.getString("u.nombre"));
		usuario.setApellidos(rs.getString("u.apellidos"));
		usuario.setEmail(rs.getString("u.email"));
		usuario.setRol(rol);
		usuario.setEdad(rs.getInt("u.edad"));
		usuario.setFechaRegistro(rs.getDate("u.fecha_registro").toLocalDate());
        return usuario;
    }
    
}
