package com.juegosdemesa.tinyboardgames.controladores.admin;

import com.juegosdemesa.tinyboardgames.entidades.Juego;
import com.juegosdemesa.tinyboardgames.repositorios.Daos.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


public class JuegosAdminController {

    @Autowired
	private Dao<Juego> dao;

	@GetMapping
	public String listarJuegos(Model modelo) {
		modelo.addAttribute("juegos", dao.obtenerTodos());
		return "admin/juegos/listarJuegos";
	}

	// @GetMapping("/editarJuego/{id}")
	// public String editarJuegos(@PathVariable Long id, Model model) {
	// 	model.addAttribute("juego", dao.obtenerPorId(id));
	// 	return "admin/editarJuego";
	// }

	// @PostMapping("/guardarJuego")
	// public String guardarUsuario(@ModelAttribute Juego juego, Model modelo) {
	// 	Long idUsuario = juego.getId();
	// 	String nombre = juego.getNombre();
	// 	String apellidos = juego.getApellidos();
	// 	int edad = Integer.parseInt(usuario.getEdad().toString());

	// 	juego = new Juego(idUsuario,nombre, apellidos, edad);
	// 	{
	// 		dao.editar(juego);
	// 		modelo.addAttribute("usuarios", dao.obtenerTodos());
	// 		return "admin/listarUsuarios";
	// 	}

	// }

	@GetMapping("/eliminar/{id}")
	public String eliminarJuegos(@PathVariable Long id, Model modelo) {
		dao.borrar(id);
		modelo.addAttribute("juegos", dao.obtenerTodos());
		return "admin/listarJuegos";
	}


    
}
