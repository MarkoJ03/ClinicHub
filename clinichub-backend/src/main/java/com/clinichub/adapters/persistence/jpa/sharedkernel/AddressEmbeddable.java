package com.clinichub.adapters.persistence.jpa.sharedkernel;



import com.clinichub.adapters.persistence.reference.jpa.CityJpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class AddressEmbeddable {
    private String street;
    private String number;
    private String zip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityJpa city;
}
