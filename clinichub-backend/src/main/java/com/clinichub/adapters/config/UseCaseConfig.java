package com.clinichub.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.clinichub.core.sharedkernel.port.PersonRepository;
import com.clinichub.core.sharedkernel.usecase.CreatePerson;
import com.clinichub.core.sharedkernel.usecase.UpdatePerson;
import com.clinichub.core.sharedkernel.usecase.GetPerson;
import com.clinichub.core.sharedkernel.usecase.ListPersons;


import com.clinichub.core.reference.usecase.CreateCountry;
import com.clinichub.core.reference.usecase.GetCountry;
import com.clinichub.core.reference.usecase.ListCountries;
import com.clinichub.core.reference.usecase.UpdateCountry;
import com.clinichub.core.reference.port.CountryRepository;

import com.clinichub.shared.mappers.CountryMapper;


import com.clinichub.core.reference.usecase.CreateCity;
import com.clinichub.core.reference.usecase.GetCity;
import com.clinichub.core.reference.usecase.ListCities;
import com.clinichub.core.reference.usecase.UpdateCity;
import com.clinichub.adapters.persistence.reference.adapter.CityRepositoryAdapter;
import com.clinichub.adapters.persistence.reference.adapter.CountryRepositoryAdapter;
import com.clinichub.adapters.persistence.reference.jpa.CitySpringRepository;
import com.clinichub.adapters.persistence.reference.jpa.CountrySpringRepository;
import com.clinichub.core.reference.port.CityRepository;

import com.clinichub.shared.mappers.CityMapper;

@Configuration
public class UseCaseConfig {
	
	// Person use cases

    @Bean CreatePerson createPerson(PersonRepository repo) { return new CreatePerson(repo); }

    @Bean UpdatePerson updatePerson(PersonRepository repo) { return new UpdatePerson(repo); }

    @Bean GetPerson getPerson(PersonRepository repo) { return new GetPerson(repo); }

    @Bean ListPersons listPersons(PersonRepository repo) { return new ListPersons(repo); }

    // Country use cases
    @Bean CountryRepository countryRepository(CountrySpringRepository springRepo, CountryMapper mapper) {
        return new CountryRepositoryAdapter(springRepo, mapper);
    }

    @Bean CreateCountry createCountry(CountryRepository repo) { return new CreateCountry(repo); }
    @Bean GetCountry getCountry(CountryRepository repo) { return new GetCountry(repo); }
    @Bean ListCountries listCountries(CountryRepository repo) { return new ListCountries(repo); }
    @Bean UpdateCountry updateCountry(CountryRepository repo) { return new UpdateCountry(repo); }

    // City use cases
    @Bean CityRepository cityRepository(CitySpringRepository springRepo, CityMapper mapper) {
        return new CityRepositoryAdapter(springRepo, mapper);
    }

    @Bean CreateCity createCity(CityRepository cityRepo, CountryRepository countryRepo) { 
        return new CreateCity(cityRepo, countryRepo); 
    }
    @Bean GetCity getCity(CityRepository repo) { return new GetCity(repo); }
    @Bean ListCities listCities(CityRepository repo) { return new ListCities(repo); }
    @Bean UpdateCity updateCity(CityRepository cityRepo, CountryRepository countryRepo) { 
        return new UpdateCity(cityRepo, countryRepo); 
    }
}