package com.clinichub.shared.mappers;


import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.CountryId;
import com.clinichub.adapters.persistence.reference.jpa.CityJpa;
import com.clinichub.core.reference.domain.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    
    public City toDomain(CityJpa jpa) {
        if (jpa == null) return null;
        
        return new City(
            new CityId(jpa.getId()),
            jpa.getName(),
            new CountryId(jpa.getCountry().getId())
        );
    }
    
    public CityJpa toJpa(City domain) {
        if (domain == null) return null;
        
        return CityJpa.builder()
            .id(domain.getId().getValue())
            .name(domain.getName())
            .country(com.clinichub.adapters.persistence.reference.jpa.CountryJpa.builder()
                .id(domain.getCountryId().getValue())
                .build())
            .build();
    }
}


