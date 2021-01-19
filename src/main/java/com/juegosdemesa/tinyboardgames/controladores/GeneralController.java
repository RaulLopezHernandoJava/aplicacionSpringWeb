package com.juegosdemesa.tinyboardgames.controladores;

import com.juegosdemesa.tinyboardgames.entidades.Juego;
import com.juegosdemesa.tinyboardgames.entidades.Usuario;
import com.juegosdemesa.tinyboardgames.repositorios.Daos.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GeneralController {

	@Autowired
	private Dao<Juego> dao;


	@GetMapping("/")
	public String portada() {
		return "general/portada";
	}


	@GetMapping("/general/listarJuegos")
	public String listarJuegos(Model modelo) {
		modelo.addAttribute("juegos", dao.obtenerTodos());
		return "general/listarJuegos";
	}

	@GetMapping("/general/registro")
	public String registro(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "general/registroUsuario";
	}
}
