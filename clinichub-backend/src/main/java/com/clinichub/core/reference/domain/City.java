package com.clinichub.core.reference.domain;

import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.CountryId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class City {
    
    private CityId id;
    private String name;
    private CountryId countryId;
    
    public City(CityId id, String name, CountryId countryId) {
        setName(name);
        if (countryId == null) throw new IllegalArgumentException("countryId");
        this.id = id;
        this.countryId = countryId;
    }
    
    public void changeName(String name) {
        setName(name);
    }
    
    public void changeCountry(CountryId countryId) {
        if (countryId == null) throw new IllegalArgumentException("countryId");
        this.countryId = countryId;
    }
    
    private void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name");
        this.name = name.trim();
    }
}
