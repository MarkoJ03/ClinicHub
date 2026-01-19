package com.clinichub.adapters.web.tenant.dto;

import lombok.Data;
import lombok.Builder;


@Data
@Builder
public class TenantDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private AddressDto address;
    private String logo;
    private boolean active;



@Data
@Builder
public static class AddressDto {
    private String street;
    private String number;
    private String zip;
    private CityDto city;
    }


    @Data
    @Builder
    public static class CityDto {
        private Long id;
        private String name;
        private CountryDto country;  
    }
    
    @Data
    @Builder
    public static class CountryDto {
        private Long id;
        private String name;
}
}