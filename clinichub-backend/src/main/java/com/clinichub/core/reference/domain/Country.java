package com.clinichub.core.reference.domain;

import com.clinichub.core.shared.types.CountryId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class Country {
    
    private CountryId id;
    private String name;
    
    public Country(CountryId id, String name) {
        setName(name);
        this.id = id;
    }
    
    public void changeName(String name) {
        setName(name);
    }
    
    private void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name");
        this.name = name.trim();
    }
}
