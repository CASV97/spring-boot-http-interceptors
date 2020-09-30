package com.bolsadeideas.springboot.form.app.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.entity.User;

/**
 * Vamos a necesitar 2 metodos handler un GET para mostrar el formulario
 * <code>(@GetMapping)</code> y POST para procesar o recibir los datos del
 * formulario <code>(@PostMapping)</code>
 * 
 */
@Controller
//se guarda los datos del objeto user en una session HTTP 
@SessionAttributes("user")
public class FormController {
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("title", "Form Usuario");
		User user = new User();
		// dato normalmente obtenido de una base de datos
		user.setIdentifier("123.456.789-k");
		model.addAttribute("user", user);
		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid User user, BindingResult bindingResult, Model model, SessionStatus status) {
		model.addAttribute("title", "Resultado Form");
		if (bindingResult.hasErrors()) {

			// El manejo de errores los podemos trabajar de forma automatica e implicita por
			// thymeleaf y Spring famework
			return "form";
		}
		model.addAttribute("user", user);
		/**
		 * completa el proceso manejo de datos y elimina los atributos u objetos
		 * almacenados en la sesion
		 */
		status.setComplete();
		return "resultform";
	}
}