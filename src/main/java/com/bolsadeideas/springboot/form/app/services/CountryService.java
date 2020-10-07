/**
 * Los Services de logica de negocio deberian operar con los datos, funcionan como contrato de implementación , 
 * es decir cuales son los métodos que vamos a implementar en nuestra clase POJO, entity concreta 
 */
package com.bolsadeideas.springboot.form.app.services;

import java.util.List;

import com.bolsadeideas.springboot.form.app.models.entity.Country;

/**
 * @author ariel
 *
 */
public interface CountryService {
	public List<Country> list();

	public Country getCountryById(Integer id);
}
