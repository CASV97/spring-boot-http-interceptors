package com.bolsadeideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.entity.Country;

@Service
public class CountryServiceImpl implements CountryService {
	private List<Country> list;

	public CountryServiceImpl() {
		this.list = Arrays.asList(new Country(1, "ES", "España"), new Country(2, "MX", "México"),
				new Country(3, "CL", "Chile"), new Country(4, "EC", "Ecuador"), new Country(5, "CO", "Colombia"),
				new Country(5, "BO", "Bolivia"), new Country(6, "FR", "Francia"));
	}

	@Override
	public List<Country> list() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public Country getCountryById(Integer id) {
		Country result = null;
		for (Country country : this.list) {
			if (id == country.getId()) {
				result = country;
				break;
			}
		}
		return result;
	}

}
