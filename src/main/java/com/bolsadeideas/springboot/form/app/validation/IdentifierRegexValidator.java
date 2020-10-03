package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**2.-*/
public class IdentifierRegexValidator  implements ConstraintValidator<IdentifierRegex, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//3.-
		if (value.matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")) {
			return true;
		}
		return false;
	}

}
