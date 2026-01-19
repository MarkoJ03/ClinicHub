package com.clinichub.adapters.persistence.tenant.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.clinichub.core.shared.types.TenantId;
import com.clinichub.core.tenant.domain.Tenant;
import com.clinichub.adapters.persistence.tenant.jpa.TenantSpringRepository;
import com.clinichub.adapters.persistence.tenant.jpa.TenantJpa;
import com.clinichub.adapters.persistence.tenant.mapper.TenantMapper;
import com.clinichub.core.tenant.port.TenantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TenantRepositoryAdapter implements TenantRepository {

    private final TenantSpringRepository springRepository;
    private final TenantMapper mapper;

    @Override
    public Tenant save(Tenant tenant) {
        TenantJpa jpa = mapper.toJpa(tenant);
        TenantJpa savedJpa = springRepository.save(jpa);
        return mapper.toDomain(savedJpa);
    }
    @Override
        public Optional<Tenant> findById(TenantId id) {
        return springRepository.findById(id.getValue())
            .map(mapper::toDomain);
    }

    @Override
    public List<Tenant> findAll() {
        return springRepository.findAll().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Tenant> findByName(String name) {
        return springRepository.findByName(name)
            .map(mapper::toDomain);
    }

    @Override
    public boolean existsByName(String name) {
        return springRepository.existsByName(name);
    }

    @Override
    public void deleteById(TenantId id) {
        springRepository.deleteById(id.getValue());
    }
}  