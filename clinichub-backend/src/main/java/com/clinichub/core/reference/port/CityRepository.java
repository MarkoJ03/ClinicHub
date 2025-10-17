package com.clinichub.core.reference.port;

import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    City save(City city);
    Optional<City> findById(CityId id);
    List<City> findAll();
    List<City> findByCountryId(CountryId countryId);
    void deleteById(CityId id);
    boolean existsById(CityId id);
    Optional<City> findByNameAndCountryId(String name, CountryId countryId);
}



