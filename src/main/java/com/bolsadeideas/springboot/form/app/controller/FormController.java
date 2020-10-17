package com.bolsadeideas.springboot.form.app.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.bolsadeideas.springboot.form.app.editors.RolesEditor;
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

	@Autowired
	private RolesEditor rolesEditor;

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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		binder.registerCustomEditor(String.class, "username", new UpperCaseUsernameEditor());
		binder.registerCustomEditor(Country.class, "country", countryEditor);

		binder.registerCustomEditor(Role.class, "roles", rolesEditor);

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

	@ModelAttribute("genders")
	public List<String> genders() {
		return Arrays.asList("Male", "Female");

	}

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("title", "Form Usuario");

		User user = new User();
		user.setIdentifier("23.456.789-K");
		user.setEnable(true);
		user.setSecretValue("valorUltrasecreto1684asd351d↕ýTD╣▀");
		/*
		 * para dejar seleccionado un país por defecto es importante que el metodo
		 * toString del objeto Country de vuelva su id
		 */
		user.setCountry(new Country(1, "ES", "España"));
		/*
		 * En la vista para poblar el select con el valor por defecto es necesario hacer
		 * uso del atributo de thymelaf th:checked="${#list.contains(user.roles,role)}"
		 * #list.contains(List , item ) esta utilidad compara si el objeto role que
		 * estamos marcando por defecto existe dentro de la lista de roles, pero para
		 * ello tenemos que implementar el método equals() en la clase Role
		 */
		user.setRoles(Arrays.asList(new Role(2, "User", "ROLE_USER")));

		model.addAttribute("user", user);
		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid User user, BindingResult bindingResult, Model model, SessionStatus status) {
		model.addAttribute("title", "Resultado Form");
		if (bindingResult.hasErrors()) {
			return "form";
		}
		model.addAttribute("user", user);
		/*
		 * completa el proceso manejo de datos y elimina los atributos u objetos
		 * almacenados en la sesion
		 */
		status.setComplete();
		return "resultform";
	}
}