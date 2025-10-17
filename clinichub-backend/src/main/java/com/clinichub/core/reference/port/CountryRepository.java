package com.clinichub.core.reference.port;

import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    Country save(Country country);
    Optional<Country> findById(CountryId id);
    List<Country> findAll();
    void deleteById(CountryId id);
    boolean existsById(CountryId id);
    Optional<Country> findByName(String name);
}



