package com.clinichub.adapters.persistence.tenant.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantSpringRepository extends JpaRepository<TenantJpa, Long> {

    Optional<TenantJpa> findByName(String name);
    boolean existsByName(String name);

}
