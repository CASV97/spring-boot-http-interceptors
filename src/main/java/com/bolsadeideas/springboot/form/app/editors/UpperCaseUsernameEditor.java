/**
 * Clase Filtro encargada de modificar el username de un usuario y cambiarlo a mayúsculas
 */
package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

/**
 * @author ariel
 * 
 *
 */
public class UpperCaseUsernameEditor extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(text.toUpperCase().trim());
	}
	
}
