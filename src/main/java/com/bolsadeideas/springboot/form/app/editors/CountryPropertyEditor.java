package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.form.app.services.CountryService;

@Component
public class CountryPropertyEditor extends PropertyEditorSupport {
	@Autowired
	private CountryService countryService;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {
		if (idString != null && idString.length() > 0) {
			try {
				Integer id = Integer.parseInt(idString);
				this.setValue(countryService.getCountryById(id));

			} catch (NumberFormatException e) {
				setValue(null);
			}
		} else {
			setValue(null);
		}
	}

}
