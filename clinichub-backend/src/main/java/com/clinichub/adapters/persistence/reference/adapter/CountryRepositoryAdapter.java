package com.clinichub.adapters.persistence.reference.adapter;

import com.clinichub.adapters.persistence.reference.jpa.CountryJpa;
import com.clinichub.adapters.persistence.reference.jpa.CountrySpringRepository;
import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.Country;
import com.clinichub.core.reference.port.CountryRepository;
import com.clinichub.shared.mappers.CountryMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CountryRepositoryAdapter implements CountryRepository {
    
    private final CountrySpringRepository springRepository;
    private final CountryMapper mapper;
    
    @Override
    public Country save(Country country) {
        CountryJpa jpa = mapper.toJpa(country);
        CountryJpa savedJpa = springRepository.save(jpa);
        return mapper.toDomain(savedJpa);
    }
    
    @Override
    public Optional<Country> findById(CountryId id) {
        return springRepository.findById(id.getValue())
            .map(mapper::toDomain);
    }
    
    @Override
    public List<Country> findAll() {
        return springRepository.findAll().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(CountryId id) {
        springRepository.deleteById(id.getValue());
    }
    
    @Override
    public boolean existsById(CountryId id) {
        return springRepository.existsById(id.getValue());
    }
    
    @Override
    public Optional<Country> findByName(String name) {
        return springRepository.findByName(name)
            .map(mapper::toDomain);
    }
}





