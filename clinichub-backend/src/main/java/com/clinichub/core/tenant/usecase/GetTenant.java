package com.clinichub.core.tenant.usecase;

import java.util.Optional;

import com.clinichub.core.shared.types.TenantId;
import com.clinichub.core.tenant.domain.Tenant;
import com.clinichub.core.tenant.port.TenantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetTenant {
    private final TenantRepository tenantRepository;

    public Optional<Tenant> execute(TenantId id) {
        return tenantRepository.findById(id);
    }

}
