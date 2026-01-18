package com.clinichub.shared.mappers;

import com.clinichub.adapters.web.reference.dto.CountryDto;
import com.clinichub.core.shared.types.CountryId;
import com.clinichub.core.reference.domain.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryDtoMapper {
    
    public CountryDto toDto(Country domain) {
        if (domain == null) return null;
        
        return CountryDto.builder()
            .id(domain.getId().getValue())
            .name(domain.getName())
            .build();
    }
    
    public Country toDomain(CountryDto dto) {
        if (dto == null) return null;
        
        return new Country(
            new CountryId(dto.getId()),
            dto.getName()
        );
    }
}





