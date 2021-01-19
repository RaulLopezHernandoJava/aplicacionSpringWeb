package com.juegosdemesa.tinyboardgames.repositorios.DaoMySql;

import javax.sql.DataSource;

import com.juegosdemesa.tinyboardgames.entidades.*;
import com.juegosdemesa.tinyboardgames.repositorios.Daos.DaoJuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JuegoDaoMySql implements DaoJuego {
     @Autowired
	 private DataSource dataSource;

	@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
	public Iterable<Juego> obtenerTodos() {

		String sql = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id WHERE j.active = TRUE";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {

			 Mecanica mecanica;
			 Juego juego;

			 mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"), rs.getString("m.descripcion"));
             juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),
             rs.getString("j.editorial"), mecanica, rs.getDouble("j.precio"),
             rs.getDate("j.fecha_publicacion").toLocalDate(), rs.getBoolean("j.active"));

		     return juego;
		});
    }

    
}
