
package com.clinichub.adapters.persistence.sharedkernel.jpa;

import com.clinichub.adapters.persistence.reference.jpa.CityJpa;
import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class AddressEmbeddable {

    @Column(nullable = false)
    private String street;

    private String number;

    @Column(nullable = false)
    private String zip;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private CityJpa city;
}
