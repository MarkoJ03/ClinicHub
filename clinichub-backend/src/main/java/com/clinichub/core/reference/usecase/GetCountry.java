package com.clinichub.core.reference.usecase;

import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.Country;
import com.clinichub.core.reference.port.CountryRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetCountry {
    
    private final CountryRepository countryRepository;
    
    public Optional<Country> execute(CountryId id) {
        return countryRepository.findById(id);
    }
}


