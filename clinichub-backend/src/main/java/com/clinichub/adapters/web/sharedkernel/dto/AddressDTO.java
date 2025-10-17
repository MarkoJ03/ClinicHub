package com.clinichub.adapters.web.sharedkernel.dto;





public record AddressDTO(
	    String street,
	    String number,
	    String zip,
	    Long cityId,
	    String cityName,
	    Long countryId,
	    String countryName
	) {}
