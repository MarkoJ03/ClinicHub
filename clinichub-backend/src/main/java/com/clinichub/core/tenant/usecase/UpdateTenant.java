package com.clinichub.core.tenant.usecase;

import java.util.Optional;

import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.TenantId;
import com.clinichub.core.sharedkernel.domain.Address;
import com.clinichub.core.tenant.domain.Tenant;
import com.clinichub.core.tenant.port.TenantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateTenant {
    
    private final TenantRepository tenantRepository;
    
    public Optional<Tenant> execute(Command command) {
        
        
        Optional<Tenant> existing = tenantRepository.findById(command.id());
        
        if (existing.isEmpty()) {
            return Optional.empty();  
        }
        
        Tenant tenant = existing.get();

  
        if (!tenant.getName().equals(command.name())) {
            if (tenantRepository.existsByName(command.name())) {
                throw new IllegalArgumentException("Tenant with this name already exists");
            }
        }

        tenant.changeName(command.name());
        tenant.changeEmail(command.email());
        tenant.changePhone(command.phone());
        tenant.changeAddress(new Address(command.street(), command.number(), command.zip(), command.cityId()));
        tenant.changeLogo(command.logo());
        return Optional.of(tenantRepository.save(tenant));


    }







        public record Command(
            TenantId id,
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