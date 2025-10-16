package com.clinichub.adapters.web.sharedkernel.dto;



import com.clinichub.adapters.web.reference.dto.CityDto;

import lombok.Data;

@Data
public class AddressDTO {
	private String street;
    private String number;
    private String zip;
    private CityDto city;
}
