package com.clinichub.adapters.web.tenant.mapper;

import org.springframework.stereotype.Component;

import com.clinichub.adapters.web.tenant.dto.TenantDto;
import com.clinichub.core.tenant.domain.Tenant;
import com.clinichub.core.reference.domain.City;
import com.clinichub.core.reference.domain.Country;
import com.clinichub.core.reference.port.CityRepository;
import com.clinichub.core.reference.port.CountryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TenantDtoMapper {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    
   


    public TenantDto toDto(Tenant domain) {
        if (domain == null) return null;
        
        City city = cityRepository.findById(domain.getAddress().getCityId()).orElseThrow(() -> new RuntimeException("City not found"));
        Country country = countryRepository.findById(city.getCountryId()).orElseThrow(() -> new RuntimeException("Country not found"));

        TenantDto.CountryDto countryDto = TenantDto.CountryDto.builder()
        .id(country.getId().getValue())
        .name(country.getName())
        .build();
    
    TenantDto.CityDto cityDto = TenantDto.CityDto.builder()
        .id(city.getId().getValue())
        .name(city.getName())
        .country(countryDto)
        .build();

        TenantDto.AddressDto addressDto = TenantDto.AddressDto.builder()
            .street(domain.getAddress().getStreet())
            .number(domain.getAddress().getNumber())
            .zip(domain.getAddress().getZip())
            .city(cityDto)
            .build();
        
        return TenantDto.builder()
            .id(domain.getId().getValue())
            .name(domain.getName())
            .email(domain.getEmail())
            .phone(domain.getPhone())
            .logo(domain.getLogo())
            .active(domain.isActive())
            .address(addressDto)
            .build();
    }
}
