package com.clinichub.core.sharedkernel.usecase;

import java.util.List;

import com.clinichub.core.sharedkernel.domain.Person;
import com.clinichub.core.sharedkernel.port.PersonRepository;

public class ListPersons {
 private final PersonRepository repo;
 public ListPersons(PersonRepository repo){ this.repo = repo; }
 public List<Person> handle(int offset, int limit){
     return repo.findAll();
 }
}
