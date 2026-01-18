package com.clinichub.core.tenant.port;

import java.util.List;
import java.util.Optional;

import com.clinichub.core.shared.types.TenantId;
import com.clinichub.core.tenant.domain.Tenant;

public interface TenantRepository {

    Tenant save(Tenant tenant);
    
    Optional<Tenant> findById(TenantId id);
    List<Tenant> findAll();

    Optional<Tenant> findByName(String name);

    boolean existsByName(String name);
    
    void deleteById(TenantId id);
}
