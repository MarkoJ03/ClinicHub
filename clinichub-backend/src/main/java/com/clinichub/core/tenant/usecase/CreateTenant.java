package com.clinichub.core.tenant.usecase;


import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.TenantId;
import com.clinichub.core.sharedkernel.domain.Address;
import com.clinichub.core.tenant.domain.Tenant;
import com.clinichub.core.tenant.port.TenantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class CreateTenant {

    private final TenantRepository tenantRepository;




    public Tenant execute(Command command) {
        if (tenantRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Tenant with this name already exists");
        }
        Address address = new Address(command.street(), command.number(), command.zip(), command.cityId());
        Tenant tenant = new Tenant(new TenantId(null), command.name(), command.email(), command.phone(), address, command.logo());

        return tenantRepository.save(tenant);
    }



public record Command(
    String name,
    String email,
    String phone,
    String street,
    String number,
    String zip,
    CityId cityId,
    String logo
) {}

}