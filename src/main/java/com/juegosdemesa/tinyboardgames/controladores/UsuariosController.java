package com.juegosdemesa.tinyboardgames.controladores;

import com.juegosdemesa.tinyboardgames.entidades.Usuario;
import com.juegosdemesa.tinyboardgames.repositorios.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsuariosController {
	@Autowired
	private Dao<Usuario> dao;
	
	@GetMapping("/")
	public String usuarios(Model modelo) {
		modelo.addAttribute("usuarios", dao.obtenerTodos());
		return "usuarios";
	}

}
