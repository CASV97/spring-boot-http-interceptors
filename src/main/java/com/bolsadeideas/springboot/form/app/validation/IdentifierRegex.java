/**
 * 
 */
package com.bolsadeideas.springboot.form.app.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
//4.- enlazar la anotacion con la clase
@Constraint(validatedBy = IdentifierRegexValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
/**
 * 1.- otra forama de implementar una validación personalizada, por ejemplo para
 * validad alguna exprecion regular esta notacion se aplica a tributos, pero se
 * puede utilizar en varios atributos con Retention decimos en que tiempo se
 * ejecuta y resulve la anotación
 * 
 */
public @interface IdentifierRegex {
	// 5.- tenemos que agregar unos atributos y metodos a la anotacion

	String message() default "Identificador inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
