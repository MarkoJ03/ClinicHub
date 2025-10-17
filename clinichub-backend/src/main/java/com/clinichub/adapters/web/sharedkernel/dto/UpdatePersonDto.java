
package com.clinichub.adapters.web.sharedkernel.dto;

public record UpdatePersonDto(
    String firstName,
    String lastName,
    String email,
    String phone,
    String street,
    String number,
    String zip,
    Long cityId
) {}
