package com.clinichub.core.reference.usecase;

import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.City;
import com.clinichub.core.reference.port.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListCities {
    
    private final CityRepository cityRepository;
    
    public List<City> execute() {
        return cityRepository.findAll();
    }
    
    public List<City> execute(CountryId countryId) {
        return cityRepository.findByCountryId(countryId);
    }
}


