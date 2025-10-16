package com.clinichub.core.reference.usecase;

import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.City;
import com.clinichub.core.reference.port.CityRepository;
import com.clinichub.core.reference.port.CountryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCity {
    
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    
    public City execute(Command command) {
        // Proveri da li država postoji
        if (countryRepository.findById(command.countryId()).isEmpty()) {
            throw new IllegalArgumentException("Country not found");
        }
        
        // Proveri da li grad već postoji u toj državi
        if (cityRepository.findByNameAndCountryId(command.name(), command.countryId()).isPresent()) {
            throw new IllegalArgumentException("City with this name already exists in this country");
        }
        
        City city = new City(
            command.id(),
            command.name(),
            command.countryId()
        );
        
        return cityRepository.save(city);
    }
    
    public record Command(
        CityId id,
        String name,
        CountryId countryId
    ) {}
}


