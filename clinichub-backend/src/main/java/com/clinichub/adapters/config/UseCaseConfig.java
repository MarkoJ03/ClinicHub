package com.clinichub.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.clinichub.core.sharedkernel.port.PersonRepository;
import com.clinichub.core.sharedkernel.usecase.CreatePerson;
import com.clinichub.core.sharedkernel.usecase.DeletePerson;
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

import com.clinichub.core.tenant.port.TenantRepository;
import com.clinichub.core.tenant.usecase.CreateTenant;
import com.clinichub.core.tenant.usecase.GetTenant;
import com.clinichub.core.tenant.usecase.ListTenants;
import com.clinichub.core.tenant.usecase.UpdateTenant;


import com.clinichub.adapters.persistence.tenant.mapper.TenantMapper;
import com.clinichub.adapters.persistence.tenant.jpa.TenantSpringRepository;
import com.clinichub.adapters.persistence.tenant.adapter.TenantRepositoryAdapter;

@Configuration
public class UseCaseConfig {

    // Tenant use cases
    @Bean TenantRepository tenantRepository(TenantSpringRepository springRepo, TenantMapper mapper) {
        return new TenantRepositoryAdapter(springRepo, mapper);
    }

    @Bean CreateTenant createTenant(TenantRepository repo) { return new CreateTenant(repo); }
    @Bean GetTenant getTenant(TenantRepository repo) { return new GetTenant(repo); }
    @Bean ListTenants listTenants(TenantRepository repo) { return new ListTenants(repo); }
    @Bean UpdateTenant updateTenant(TenantRepository repo) { return new UpdateTenant(repo); }
	
	// Person use cases
    @Bean DeletePerson deletePerson(PersonRepository repo) { return new DeletePerson(repo); } // NOVO

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