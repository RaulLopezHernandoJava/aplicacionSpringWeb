package com.juegosdemesa.tinyboardgames.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.juegosdemesa.tinyboardgames.entidades.*;
import com.juegosdemesa.tinyboardgames.repositorios.Daos.Dao;
import com.juegosdemesa.tinyboardgames.repositorios.Daos.DaoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuariosController {
	@Autowired
	private Dao<Usuario> dao;

	@Autowired
	private DaoUsuario daoUsuario;

	@GetMapping("/usuarios")
	public String usuarios(Model modelo) {
		modelo.addAttribute("usuarios", dao.obtenerTodos());
		return "usuarios";
	}

	@GetMapping("/{id}")
	public String obtenerUsuario(@PathVariable Long id, Model modelo) {
		Usuario usuario = dao.obtenerPorId(id);

		modelo.addAttribute("usuario", usuario);
		return "usuario";
	}

	@PostMapping("/guardar")
	public String guardarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirAttrs) {
		String nombre = usuario.getNombre();
		String apellidos = usuario.getApellidos();
		String email = usuario.getEmail();
		String password = usuario.getPassword();
		int edad = Integer.parseInt(usuario.getEdad().toString());
		Rol rolUsuario = daoUsuario.obtenerRolUsuario(2L);
		LocalDate fechaRegistro = LocalDate.parse(LocalDate.now().toString(),
				DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		String passwordEncriptado = BCrypt.hashpw(password, BCrypt.gensalt(10));

		usuario = new Usuario(nombre, apellidos, email, passwordEncriptado, rolUsuario, edad, fechaRegistro);
		if (usuario != null) {
			dao.insertar(usuario);
		}

		if (usuario == null) {
			redirAttrs.addFlashAttribute("defeat", "Se ha producido un error al registrarse");
			return "redirect:/";
		}

			redirAttrs.addFlashAttribute("success",
					"Se ha registrado correctamente. Haga click en Login para logearse");

			return "redirect:/";
		}
	}


