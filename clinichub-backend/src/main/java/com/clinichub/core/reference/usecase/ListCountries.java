package com.clinichub.core.reference.usecase;

import com.clinichub.core.reference.domain.Country;
import com.clinichub.core.reference.port.CountryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListCountries {
    
    private final CountryRepository countryRepository;
    
    public List<Country> execute() {
        return countryRepository.findAll();
    }
}


