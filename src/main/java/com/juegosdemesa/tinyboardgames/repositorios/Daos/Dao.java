package com.juegosdemesa.tinyboardgames.repositorios.Daos;

import com.juegosdemesa.tinyboardgames.repositorios.AccesoDatosException;

public interface Dao<T> {

	default Iterable<T> obtenerTodos() {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	};

	default T obtenerPorId(Long id) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	};

	default T insertar(T objeto) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	};

	default String editar(T objeto) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	};

	default void borrar(Long id) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	};

}
