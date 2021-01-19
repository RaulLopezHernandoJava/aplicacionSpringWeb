package com.juegosdemesa.tinyboardgames.repositorios.DaoMySql;

import java.util.*;
import javax.sql.DataSource;
import com.juegosdemesa.tinyboardgames.entidades.*;
import com.juegosdemesa.tinyboardgames.repositorios.Daos.DaoUsuario;
import com.juegosdemesa.tinyboardgames.utilities.UsuarioRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDaoMySql implements DaoUsuario {

	@Autowired
	private DataSource dataSource;

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

	@Override
	public Usuario obtenerPorId(Long id) {
		String sql = "SELECT * FROM usuarios u JOIN roles r ON u.id_rol = r.id WHERE u.id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new UsuarioRowMapper());

	}

	@Override
	public Usuario insertar(Usuario usuario) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("usuarios")
				.usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<>(1);

		parameters.put("nombre", usuario.getNombre());
		parameters.put("apellidos", usuario.getApellidos());
		parameters.put("email", usuario.getEmail());
		parameters.put("id_rol", usuario.getRol().getId());
		parameters.put("password", usuario.getPassword());
		parameters.put("edad", usuario.getEdad());
		parameters.put("fecha_registro", usuario.getFechaRegistro());

		Long newId = simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
		usuario.setId(newId);

		return usuario;
	}

	@Override
	public String editar(Usuario usuario) {
		System.out.println(usuario);
		jdbcTemplate.update("UPDATE usuarios SET nombre = ?, apellidos = ?, edad = ? WHERE id = ?",
				new Object[] { usuario.getNombre(), usuario.getApellidos(), usuario.getEdad(), usuario.getId() });
		return "/admin/listarUsuarios";
	}

	@Override
	public void borrar(Long id) {
		jdbcTemplate.update("DELETE  FROM usuarios WHERE id = ?", new Object[] { id });
	}

	@Override
	public Rol obtenerRolUsuario(Long rolUsuario) {
		String sql = "SELECT * FROM roles r WHERE r.id = ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { rolUsuario },
				(rs, rowNum) -> new Rol(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion")));

	}

	@Override
	public Usuario obtenerUsuarioPorId(Long id) {
		String sql = "SELECT * FROM usuarios r WHERE u.email = ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { id }, Usuario.class);
	}

}
