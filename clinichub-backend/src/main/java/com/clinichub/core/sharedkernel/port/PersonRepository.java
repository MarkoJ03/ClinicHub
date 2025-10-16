package com.clinichub.core.sharedkernel.port;

import java.util.List;
import java.util.Optional;

import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.domain.Person;

public interface PersonRepository {
	List<Person> findAll();
    Optional<Person> findById(PersonId id);
    Person save(Person person);
    boolean existsByEmail(String email);
    Optional<Person> findByEmail(String email);
}

