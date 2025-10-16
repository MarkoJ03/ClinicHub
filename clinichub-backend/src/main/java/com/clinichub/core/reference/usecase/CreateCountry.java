package com.clinichub.core.reference.usecase;

import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.Country;
import com.clinichub.core.reference.port.CountryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCountry {
    
    private final CountryRepository countryRepository;
    
    public Country execute(Command command) {
        if (countryRepository.findByName(command.name()).isPresent()) {
            throw new IllegalArgumentException("Country with this name already exists");
        }
        
        Country country = new Country(
            command.id(),
            command.name()
        );
        
        return countryRepository.save(country);
    }
    
    public record Command(
        CountryId id,
        String name
    ) {}
}


