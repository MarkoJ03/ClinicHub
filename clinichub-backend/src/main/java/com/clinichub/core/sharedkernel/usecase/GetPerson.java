package com.clinichub.core.sharedkernel.usecase;

import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.domain.Person;
import com.clinichub.core.sharedkernel.port.PersonRepository;

public class GetPerson {
 private final PersonRepository repo;
 public GetPerson(PersonRepository repo){ this.repo = repo; }
 public Person handle(PersonId id){
     return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
 }
}

