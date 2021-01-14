package com.juegosdemesa.tinyboardgames.controladores.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UsuariosAdminController {
	@GetMapping //("/comics")
	public String usuarios() {
		return "admin/usuarios";
	}	
}
