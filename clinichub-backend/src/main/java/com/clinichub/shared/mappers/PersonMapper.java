
package com.clinichub.shared.mappers;


import com.clinichub.adapters.persistence.sharedkernel.jpa.AddressEmbeddable;
import com.clinichub.adapters.persistence.sharedkernel.jpa.PersonJpa;
import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.domain.Address;
import com.clinichub.core.sharedkernel.domain.Person;

public final class PersonMapper {

    private PersonMapper() {}

    public static Person toDomain(PersonJpa e) {
        CityId cityId = null;
        AddressEmbeddable a = e.getAddress();
        if (a != null && a.getCity() != null) {
            cityId = new CityId(a.getCity().getId());
        }
        Address address = null;
        if (a != null) {
            address = new Address(a.getStreet(), a.getNumber(), a.getZip(), cityId);
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
        if (d.getId() != null) e.setId(d.getId().getValue());

        e.setFirstName(d.getFirstName());
        e.setLastName(d.getLastName());
        e.setEmail(d.getEmail());
        e.setPhone(d.getPhone());

        if (d.getAddress() != null) {
            AddressEmbeddable a = new AddressEmbeddable();
            a.setStreet(d.getAddress().getStreet());
            a.setNumber(d.getAddress().getNumber());
            a.setZip(d.getAddress().getZip());

            e.setAddress(a);
        }
        return e;
    }
}
