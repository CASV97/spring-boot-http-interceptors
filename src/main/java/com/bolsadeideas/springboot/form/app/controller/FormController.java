package com.bolsadeideas.springboot.form.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Vamos a necesitar 2 metodos handler un GET para mostrar el formulario
 * <code>(@GetMapping)</code> y POST para procesar o recibir los datos del
 * formulario <code>(@PostMapping)</code>
 */
@Controller
public class FormController {
	@GetMapping("/form")
	public String form(Model model) {
		return "form";
	}

	@PostMapping("/form")
	public String procesar() {
		return "resultado";
	}
}