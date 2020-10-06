package com.bolsadeideas.springboot.form.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.UpperCaseUsernameEditor;
import com.bolsadeideas.springboot.form.app.models.entity.User;
import com.bolsadeideas.springboot.form.app.validation.UserValidator;

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
	// vamos a inyectar la clase validator en concreto
	@Autowired
	private UserValidator validator;

	/**
	 * para que se valide de forma transparente sin tener que escribir lineas de
	 * código dentro del método para procesar el formulario para eso tenemos que
	 * implementar y registrar el validador en el InitBinder (es decir, cuando se
	 * inicializa el proceso de validación y el proceso de pasar los datos al objeto
	 * User ) valida de forma transparente, nota: esto por debajo se maneja con
	 * intersectores Intersectors
	 */
	@InitBinder // elemento del ciclo de vida del controlador
	public void initBinder(WebDataBinder binder) {

		binder.addValidators(validator);
		// CustomEditor cuztomizar un campo que obtemenos y convertirlo en otro tipo de
		// dato es como un filtro
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Eso es la indulgencia, es decir, define si en analizdor que va a realizar un
		// análisis un (parse) de la fecha con false se vuelve mas estricto
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		// tall como registramos un Custom editor para el validador de las fechas, asi
		// customizamos nuestros editores o filtros
		binder.registerCustomEditor(String.class, "username", new UpperCaseUsernameEditor());
	}

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("title", "Form Usuario");
		User user = new User();
		// dato normalmente obtenido de una base de datos
		user.setIdentifier("23.456.789-K");
		model.addAttribute("user", user);
		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid User user, BindingResult bindingResult, Model model, SessionStatus status) {
		// asi validamos de forma explicita
		// validator.validate(user, bindingResult);
		model.addAttribute("title", "Resultado Form");
		if (bindingResult.hasErrors()) {
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