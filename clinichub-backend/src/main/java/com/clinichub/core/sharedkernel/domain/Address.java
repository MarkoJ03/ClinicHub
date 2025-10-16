package com.clinichub.core.sharedkernel.domain;

	
import com.clinichub.core.shared.types.CityId;

import lombok.Value;

@Value		
public class Address {

	private String street;
	private String number;
	private String zip;
	private CityId cityId;
	
}
