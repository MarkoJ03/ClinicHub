package com.clinichub.adapters.persistence.jpa.sharedkernel;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.domain.Person;
import com.clinichub.core.sharedkernel.port.PersonRepository;
import com.clinichub.shared.mappers.PersonMapper;

@Repository
public class PersonRepositoryAdapter implements PersonRepository {
    private final PersonSpringRepository jpa;
    public PersonRepositoryAdapter(PersonSpringRepository jpa){ this.jpa=jpa; }

    @Override public Optional<Person> findById(PersonId id){
        return jpa.findById(id.getValue()).map(PersonMapper::toDomain);
    }
    @Override public List<Person> findAll(){
        List<PersonJpa> list = jpa.findAll();
        return list.stream().map(PersonMapper::toDomain).toList();
    }
    @Override public Optional<Person> findByEmail(String email){
        return jpa.findByEmail(email).map(PersonMapper::toDomain);
    }
    @Override public Person save(Person p){
        PersonJpa saved = jpa.save(PersonMapper.toJpa(p));
        return PersonMapper.toDomain(saved);
    }
    @Override public boolean existsByEmail(String email){ return jpa.existsByEmail(email); }
}
