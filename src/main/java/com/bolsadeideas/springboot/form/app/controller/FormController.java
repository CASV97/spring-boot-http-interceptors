package com.bolsadeideas.springboot.form.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bolsadeideas.springboot.form.app.models.entity.User;

/**
 * Vamos a necesitar 2 metodos handler un GET para mostrar el formulario
 * <code>(@GetMapping)</code> y POST para procesar o recibir los datos del
 * formulario <code>(@PostMapping)</code>
 * 
 */
@Controller
public class FormController {
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("title", "Form Usuario");
		return "form";
	}

	/*
	 * 1.- con @RequestParam T ImputName 2.- @RequestParam(name="ImputName") T
	 * ImputName
	 */
	@PostMapping("/form")
	public String procesar(@Valid User user, BindingResult bindingResult, Model model) {
		// antes de manejar,guardar,usar el objeto User tenemos si es valido o no
		// Binding result siempre tiene que ir despues del objeto pojo
		model.addAttribute("title", "Resultado Form");
		if (bindingResult.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			bindingResult.getFieldErrors().stream().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
			});
			model.addAttribute("error", errores);
			return "form";
		}
		model.addAttribute("user", user);
		return "resultform";
	}
}