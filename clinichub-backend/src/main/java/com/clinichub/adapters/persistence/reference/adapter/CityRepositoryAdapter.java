package com.clinichub.adapters.persistence.reference.adapter;

import com.clinichub.adapters.persistence.reference.jpa.CityJpa;
import com.clinichub.adapters.persistence.reference.jpa.CitySpringRepository;
import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.City;
import com.clinichub.core.reference.port.CityRepository;
import com.clinichub.shared.mappers.CityMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CityRepositoryAdapter implements CityRepository {
    
    private final CitySpringRepository springRepository;
    private final CityMapper mapper;
    
    @Override
    public City save(City city) {
        CityJpa jpa = mapper.toJpa(city);
        CityJpa savedJpa = springRepository.save(jpa);
        return mapper.toDomain(savedJpa);
    }
    
    @Override
    public Optional<City> findById(CityId id) {
        return springRepository.findById(id.getValue())
            .map(mapper::toDomain);
    }
    
    @Override
    public List<City> findAll() {
        return springRepository.findAll().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<City> findByCountryId(CountryId countryId) {
        return springRepository.findByCountryId(countryId.getValue()).stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(CityId id) {
        springRepository.deleteById(id.getValue());
    }
    
    @Override
    public boolean existsById(CityId id) {
        return springRepository.existsById(id.getValue());
    }
    
    @Override
    public Optional<City> findByNameAndCountryId(String name, CountryId countryId) {
        return springRepository.findByNameAndCountryId(name, countryId.getValue())
            .map(mapper::toDomain);
    }
}



