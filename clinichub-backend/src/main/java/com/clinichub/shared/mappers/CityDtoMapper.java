package com.clinichub.shared.mappers;

import com.clinichub.adapters.web.reference.dto.CityDto;
import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.City;
import org.springframework.stereotype.Component;

@Component
public class CityDtoMapper {
    
    public CityDto toDto(City domain) {
        if (domain == null) return null;
        
        return CityDto.builder()
            .id(domain.getId().getValue())
            .name(domain.getName())
            .countryId(domain.getCountryId().getValue())
            .build();
    }
    
    public CityDto toDtoWithCountryName(City domain, String countryName) {
        if (domain == null) return null;
        
        return CityDto.builder()
            .id(domain.getId().getValue())
            .name(domain.getName())
            .countryId(domain.getCountryId().getValue())
            .countryName(countryName)
            .build();
    }
    
    public City toDomain(CityDto dto) {
        if (dto == null) return null;
        
        return new City(
            new CityId(dto.getId()),
            dto.getName(),
            new CountryId(dto.getCountryId())
        );
    }
}



