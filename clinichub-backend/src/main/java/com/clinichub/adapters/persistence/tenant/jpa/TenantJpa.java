package com.clinichub.adapters.persistence.tenant.jpa;

import com.clinichub.adapters.persistence.sharedkernel.jpa.AddressEmbeddable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tenant")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantJpa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtenant")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Embedded
    private AddressEmbeddable address;

    @Column(name = "logo")
    private String logo;

    @Column(name = "active", nullable = false)
    private boolean active;

}
