package com.clinichub.shared.mappers;


import com.clinichub.adapters.persistence.jpa.sharedkernel.AddressEmbeddable;
import com.clinichub.adapters.persistence.jpa.sharedkernel.PersonJpa;
import com.clinichub.adapters.persistence.reference.jpa.CityJpa;
import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.domain.Address;
import com.clinichub.core.sharedkernel.domain.Person;

public final class PersonMapper {

    private PersonMapper() {} 

    public static Person toDomain(PersonJpa e) {

        Long cityId = null;
        if (e.getAddress() != null && e.getAddress().getCity() != null) {
            cityId = e.getAddress().getCity().getId();
        }

        Address address = null;
        if (e.getAddress() != null) {
            address = new Address(
                e.getAddress().getStreet(),
                e.getAddress().getNumber(),
                e.getAddress().getZip(),
                cityId != null ? new CityId(cityId) : null
            );
        }

        return new Person(
            e.getId() == null ? null : new PersonId(e.getId()),
            e.getFirstName(),
            e.getLastName(),
            e.getEmail(),
            e.getPhone(),
            address
        );
    }

    public static PersonJpa toJpa(Person d) {
        PersonJpa e = new PersonJpa();
        if (d.getId() != null) {
            e.setId(d.getId().getValue());
        }

        e.setFirstName(d.getFirstName());
        e.setLastName(d.getLastName());
        e.setEmail(d.getEmail());
        e.setPhone(d.getPhone());

 
        if (d.getAddress() != null) {
            AddressEmbeddable a = new AddressEmbeddable();
            a.setStreet(d.getAddress().getStreet());
            a.setNumber(d.getAddress().getNumber());
            a.setZip(d.getAddress().getZip());

            CityJpa cityRef = null;
            if (d.getAddress().getCityId() != null) {
                cityRef = new CityJpa();
                cityRef.setId(d.getAddress().getCityId().getValue()); 
            }
            a.setCity(cityRef);

            e.setAddress(a);
        }

        return e;
    }
}
