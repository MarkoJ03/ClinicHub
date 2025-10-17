package com.clinichub.shared.mappers;


import com.clinichub.core.shared.types.CountryId;
import com.clinichub.adapters.persistence.reference.jpa.CountryJpa;
import com.clinichub.core.reference.domain.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    
    public Country toDomain(CountryJpa jpa) {
        if (jpa == null) return null;
        
        return new Country(
            new CountryId(jpa.getId()),
            jpa.getName()
        );
    }
    
    public CountryJpa toJpa(Country domain) {
        if (domain == null) return null;
        
        return CountryJpa.builder()
            .id(domain.getId().getValue())
            .name(domain.getName())
            .build();
    }
}