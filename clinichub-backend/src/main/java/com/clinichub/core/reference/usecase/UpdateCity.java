package com.clinichub.core.reference.usecase;

import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.City;
import com.clinichub.core.reference.port.CityRepository;
import com.clinichub.core.reference.port.CountryRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateCity {
    
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    
    public Optional<City> execute(Command command) {
        return cityRepository.findById(command.id())
            .map(city -> {
                if (command.name() != null) {
                    city.changeName(command.name());
                }
                if (command.countryId() != null) {
                    if (countryRepository.findById(command.countryId()).isEmpty()) {
                        throw new IllegalArgumentException("Country not found");
                    }
                    city.changeCountry(command.countryId());
                }
                return cityRepository.save(city);
            });
    }
    
    public record Command(
        CityId id,
        String name,
        CountryId countryId
    ) {}
}


