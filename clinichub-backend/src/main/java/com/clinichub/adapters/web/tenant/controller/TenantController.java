package com.clinichub.adapters.web.tenant.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinichub.adapters.web.tenant.dto.CreateTenantDto;
import com.clinichub.adapters.web.tenant.dto.TenantDto;
import com.clinichub.adapters.web.tenant.dto.UpdateTenantDto;
import com.clinichub.adapters.web.tenant.mapper.TenantDtoMapper;
import com.clinichub.core.tenant.domain.Tenant;
import com.clinichub.core.tenant.usecase.CreateTenant;
import com.clinichub.core.tenant.usecase.UpdateTenant;
import com.clinichub.core.tenant.usecase.GetTenant;
import com.clinichub.core.tenant.usecase.ListTenants;
import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.TenantId;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {


    private final CreateTenant createTenant;
    private final UpdateTenant updateTenant;
    private final GetTenant getTenant;
    private final ListTenants listTenants;
    private final TenantDtoMapper tenantDtoMapper;


    @PostMapping
    public ResponseEntity<TenantDto> createTenant(@RequestBody CreateTenantDto dto) {

        CreateTenant.Command command = new CreateTenant.Command(
            dto.getName(),
            dto.getEmail(),
            dto.getPhone(),
            dto.getStreet(),
            dto.getNumber(),
            dto.getZip(),
            new CityId(dto.getCityId()),
            dto.getLogo()
        );

        Tenant tenant = createTenant.execute(command);

        TenantDto tenantDto = tenantDtoMapper.toDto(tenant);

        return ResponseEntity.status(HttpStatus.CREATED).body(tenantDto);
}


@PutMapping("/{id}")
    public ResponseEntity<TenantDto> update(
            @PathVariable Long id,
             @RequestBody UpdateTenantDto dto) {
        
        UpdateTenant.Command command = new UpdateTenant.Command(
            new TenantId(id),
            dto.getName(),
            dto.getEmail(),
            dto.getPhone(),
            dto.getStreet(),
            dto.getNumber(),
            dto.getZip(),
            new CityId(dto.getCityId()),
            dto.getLogo()
        );
        
        return updateTenant.execute(command)
            .map(tenantDtoMapper::toDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    
    }


    @GetMapping("/{id}")
    public ResponseEntity<TenantDto> get(@PathVariable Long id) {
        Optional<Tenant> tenant = getTenant.execute(new TenantId(id));
        return tenant.map(tenantDtoMapper::toDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TenantDto>> list() {
        List<Tenant> tenants = listTenants.execute();
        List<TenantDto> tenantDtos = tenants.stream()
            .map(tenantDtoMapper::toDto)
            .toList();
        return ResponseEntity.ok(tenantDtos);
    }
}