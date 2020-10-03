package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequiredValidator  implements ConstraintValidator<Required, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty() || value.isBlank()) {
			return false;
			
		}
		return true;
	}

}
