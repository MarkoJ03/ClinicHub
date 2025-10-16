package com.clinichub.core.reference.usecase;

import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.reference.domain.City;
import com.clinichub.core.reference.port.CityRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetCity {
    
    private final CityRepository cityRepository;
    
    public Optional<City> execute(CityId id) {
        return cityRepository.findById(id);
    }
}


