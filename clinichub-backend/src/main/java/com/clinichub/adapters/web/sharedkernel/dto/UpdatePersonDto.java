package com.clinichub.adapters.web.sharedkernel.dto;

public record UpdatePersonDto(
 Long id,
 String firstName,
 String lastName,
 String email,
 String phone,
 AddressDTO address
) {}