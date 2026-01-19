package com.clinichub.adapters.persistence.tenant.mapper;

import org.springframework.stereotype.Component;

import com.clinichub.adapters.persistence.reference.jpa.CityJpa;
import com.clinichub.adapters.persistence.sharedkernel.jpa.AddressEmbeddable;
import com.clinichub.adapters.persistence.tenant.jpa.TenantJpa;
import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.TenantId;
import com.clinichub.core.sharedkernel.domain.Address;
import com.clinichub.core.tenant.domain.Tenant;

@Component
public class TenantMapper {


    public Tenant toDomain(TenantJpa jpa) {
        if (jpa == null) return null;

        Address address = new Address(jpa.getAddress().getStreet(), jpa.getAddress().getNumber(), jpa.getAddress().getZip(), new CityId(jpa.getAddress().getCity().getId()));
        
        Tenant tenant = new Tenant(new TenantId(jpa.getId()), jpa.getName(), jpa.getEmail(), jpa.getPhone(), address, jpa.getLogo());
        
        if (!jpa.isActive()) {
            tenant.deactivate();
        }
        
        return tenant;
    }

    public TenantJpa toJpa(Tenant domain) {
        if (domain == null) return null;
        
        AddressEmbeddable addressJpa = AddressEmbeddable.builder()
        .street(domain.getAddress().getStreet())
        .number(domain.getAddress().getNumber())
        .zip(domain.getAddress().getZip())
        .city(new CityJpa(domain.getAddress().getCityId().getValue()))
        .build();

       return TenantJpa.builder()
            .id(domain.getId().getValue())  
            .name(domain.getName())
            .email(domain.getEmail())
            .phone(domain.getPhone())
            .address(addressJpa)
            .logo(domain.getLogo())
            .active(domain.isActive())
            .build();
    }
}
