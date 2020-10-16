package com.bolsadeideas.springboot.form.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.CountryPropertyEditor;
import com.bolsadeideas.springboot.form.app.editors.UpperCaseUsernameEditor;
import com.bolsadeideas.springboot.form.app.models.entity.Country;
import com.bolsadeideas.springboot.form.app.models.entity.Role;
import com.bolsadeideas.springboot.form.app.models.entity.User;
import com.bolsadeideas.springboot.form.app.services.CountryService;
import com.bolsadeideas.springboot.form.app.services.RoleService;
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
	@Autowired
	private RoleService roleServices;

	// vamos a inyectar la clase validator en concreto
	@Autowired
	private UserValidator validator;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryPropertyEditor countryEditor;

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

		// registrando editor del country en el formulario
		binder.registerCustomEditor(Country.class, countryEditor);
	}

	@ModelAttribute("rolesList")
	public List<Role> rolesList() {
		return roleServices.getRoles();
	}

	/**
	 * para el ejemplo poblamos el Select de html * normalmente vamos a buscar la
	 * lista mediante una consulta a la base de datos este método lo podemos
	 * utilizar en todos los métodos handler del controlador
	 * 
	 * @param model
	 * @return lo que retorna este método, se pasa y se guarda en la vista como
	 *         atributo y lo podemos utilizar en el formulario
	 */
	@ModelAttribute("countriesList")
	public List<Country> countriesList() {
		return countryService.list();
	}

	@ModelAttribute("countries")
	public List<String> countries() {
		return Arrays.asList("España", "México", "Chile", "Ecuador", "Colombia", "Bolivia", "Francia");
	}

	// llenando la vista Select con un Map
	@ModelAttribute("countriesMap")
	public Map<String, String> countriesMap() {
		Map<String, String> countries = new HashMap<String, String>();
		countries.put("ES", "España");
		countries.put("MX", "México");
		countries.put("CL", "Chile");
		countries.put("EC", "Ecuador");
		countries.put("CO", "Colombia");
		countries.put("BO", "Bolivia");
		countries.put("FR", "Francia");
		return countries;
	}

	// agregando lista de roles a la vista
	@ModelAttribute("rolesStringList")
	public List<String> rolesStringList() {
		List<String> roles = new ArrayList<String>();
		// ejemplo de nomenclatura de roles de Spring Security
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}

	// agregando map de roles a la vista
	@ModelAttribute("rolesMap")
	public Map<String, String> rolesMap() {
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Admin");
		roles.put("ROLE_USER", "User");
		roles.put("ROLE_MODERATOR", "Moderator");
		return roles;
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