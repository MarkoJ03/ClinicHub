package com.clinichub.core.sharedkernel.usecase;

import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.sharedkernel.domain.Address;
import com.clinichub.core.sharedkernel.domain.Person;
import com.clinichub.core.sharedkernel.port.PersonRepository;

public class CreatePerson {
 public record Command(String firstName, String lastName, String email, String phone,
                       String street, String number, String zip, CityId cityId) {}
 private final PersonRepository repo;
 public CreatePerson(PersonRepository repo){ this.repo = repo; }
 
 
 public Person handle(Command c){
     if (repo.existsByEmail(c.email())) throw new IllegalStateException("Email already in use");
     Address a = new Address(c.street(), c.number(), c.zip(), c.cityId());
     Person p = new Person(null, c.firstName(), c.lastName(), c.email(), c.phone(), a);
     return repo.save(p);
 }
}
