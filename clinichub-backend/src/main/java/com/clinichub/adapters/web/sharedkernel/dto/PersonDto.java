package com.clinichub.adapters.web.sharedkernel.dto;



public record PersonDto(
    Long id,
    String firstName,
    String lastName,
    String email,
    String phone,
    AddressDTO address
) {}
