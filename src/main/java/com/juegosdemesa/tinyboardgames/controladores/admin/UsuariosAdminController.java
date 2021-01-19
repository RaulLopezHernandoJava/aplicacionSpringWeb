package com.juegosdemesa.tinyboardgames.controladores.admin;

import com.juegosdemesa.tinyboardgames.entidades.Usuario;
import com.juegosdemesa.tinyboardgames.repositorios.Daos.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")

public class UsuariosAdminController {
	@Autowired
	private Dao<Usuario> dao;

	@GetMapping
	public String listarUsuarios(Model modelo) {
		modelo.addAttribute("usuarios", dao.obtenerTodos());
		return "admin/listarUsuarios";
	}

	@GetMapping("/editar/{id}")
	public String editarUsuarios(@PathVariable Long id, Model model) {
		model.addAttribute("usuario", dao.obtenerPorId(id));
		return "admin/editarUsuario";
	}

	@PostMapping("/guardar")
	public String guardarUsuario(@ModelAttribute Usuario usuario, Model modelo) {
		Long idUsuario = usuario.getId();
		String nombre = usuario.getNombre();
		String apellidos = usuario.getApellidos();
		int edad = Integer.parseInt(usuario.getEdad().toString());

		usuario = new Usuario(idUsuario,nombre, apellidos, edad);
		{
			dao.editar(usuario);
			modelo.addAttribute("usuarios", dao.obtenerTodos());
			return "admin/listarUsuarios";
		}

	}

	@GetMapping("/eliminar/{id}")
	public String eliminarUsuarios(@PathVariable Long id, Model modelo) {
		dao.borrar(id);
		modelo.addAttribute("usuarios", dao.obtenerTodos());
		return "admin/listarUsuarios";
	}

}
