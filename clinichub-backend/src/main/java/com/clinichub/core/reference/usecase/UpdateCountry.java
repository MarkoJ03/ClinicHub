package com.clinichub.core.reference.usecase;

import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.Country;
import com.clinichub.core.reference.port.CountryRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateCountry {
    
    private final CountryRepository countryRepository;
    
    public Optional<Country> execute(Command command) {
        return countryRepository.findById(command.id())
            .map(country -> {
                country.changeName(command.name());
                return countryRepository.save(country);
            });
    }
    
    public record Command(
        CountryId id,
        String name
    ) {}
}


