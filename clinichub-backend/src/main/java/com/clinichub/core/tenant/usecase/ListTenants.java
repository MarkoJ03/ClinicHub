package com.clinichub.core.tenant.usecase;

import java.util.List;

import com.clinichub.core.tenant.domain.Tenant;
import com.clinichub.core.tenant.port.TenantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListTenants {
    private final TenantRepository tenantRepository;

    public List<Tenant> execute() {
        return tenantRepository.findAll();
    }

}
