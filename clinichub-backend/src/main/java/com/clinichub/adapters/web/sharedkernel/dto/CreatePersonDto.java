package com.clinichub.adapters.web.sharedkernel.dto;


public record CreatePersonDto(
    String firstName,
    String lastName,
    String email,
    String phone,
    String street,
    String number,
    String zip,
    Long cityId
) {}