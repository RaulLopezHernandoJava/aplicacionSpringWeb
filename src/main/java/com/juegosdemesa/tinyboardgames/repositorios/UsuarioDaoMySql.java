package com.juegosdemesa.tinyboardgames.repositorios;

import com.juegosdemesa.tinyboardgames.entidades.Rol;
import com.juegosdemesa.tinyboardgames.entidades.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDaoMySql implements Dao<Usuario> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Iterable<Usuario> obtenerTodos() {

		String sql = "SELECT * FROM usuarios u JOIN roles r ON u.id_rol = r.id";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {

			Rol rol;
			Usuario usuario;

			rol = new Rol(rs.getLong("id"), rs.getString("r.nombre"), rs.getString("descripcion"));
			usuario = new Usuario(rs.getLong("id"), rs.getString("u.nombre"), rs.getString("apellidos"),
					rs.getString("email"), rol, rs.getInt("edad"), rs.getDate("fecha_registro").toLocalDate());

			return usuario;
		});

	}
}
