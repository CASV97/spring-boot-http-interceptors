package com.bolsadeideas.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsadeideas.springboot.form.app.models.entity.User;

/**
 * <h2>Validación personalizada Implementando la intefaz de Spring
 * <code>{@link Validator}</code></h2>
 */
@Component
public class UserValidator implements Validator {
//Sirve para indicar que clase Entity o POJO vamos a validar
	@Override
	public boolean supports(Class<?> clazz) {
		// valida que el objeto que estamos validando corresponde al tipo que queremos
		// validar y no otro
		return User.class.isAssignableFrom(clazz);
	}

	/***/
	@Override
	public void validate(Object target, Errors errors) {
		// recibimos el objeto o target que es de tipo usuario por lo tanto debemos
		// castear y podemos validar sus campos en el, objeto Errors validamos el error
//		User user = (User) target;
		// 2.-podemos utilizar la clase helper ValidationUtils de Spring **El errorCode
		// puede ser uno guardado en el messages.properties
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identifier", "required.user");
//		if (!user.getIdentifier().matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")) {
//			// 3.- siempre para rechazar una validacion que no es correcta usamos el objeto
//			// Errors.rejectValue()
//			errors.rejectValue("identifier", "pattern.user.identifier");
//		}
	}

}
