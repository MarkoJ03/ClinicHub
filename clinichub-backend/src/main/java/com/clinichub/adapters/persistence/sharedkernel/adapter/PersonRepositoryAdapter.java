package com.clinichub.adapters.persistence.sharedkernel.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clinichub.adapters.persistence.reference.jpa.CityJpa;
import com.clinichub.adapters.persistence.sharedkernel.jpa.AddressEmbeddable;
import com.clinichub.adapters.persistence.sharedkernel.jpa.PersonJpa;
import com.clinichub.adapters.persistence.sharedkernel.jpa.PersonSpringRepository;
import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.domain.Person;
import com.clinichub.core.sharedkernel.port.PersonRepository;
import com.clinichub.shared.mappers.PersonMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class PersonRepositoryAdapter implements PersonRepository {

    private final PersonSpringRepository jpa;

    @PersistenceContext
    private EntityManager em;

    public PersonRepositoryAdapter(PersonSpringRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Person> findById(PersonId id){
        return jpa.findById(id.getValue()).map(PersonMapper::toDomain);
    }

    @Override
    public List<Person> findAll(){
        List<PersonJpa> list = jpa.findAll();
        return list.stream().map(PersonMapper::toDomain).toList();
    }

    @Override
    public Optional<Person> findByEmail(String email){
        return jpa.findByEmail(email).map(PersonMapper::toDomain);
    }

    @Override
    @Transactional
    public Person save(Person p){

        PersonJpa e = PersonMapper.toJpa(p);


        if (e.getAddress() == null) {
            e.setAddress(new AddressEmbeddable());
        }
        if (p.getAddress() != null && p.getAddress().getCityId() != null) {
            CityJpa cityRef = em.getReference(CityJpa.class, p.getAddress().getCityId().getValue());
            e.getAddress().setCity(cityRef);
        }

        PersonJpa saved = jpa.save(e);
        return PersonMapper.toDomain(saved);
    }

    @Override
    public boolean existsByEmail(String email){ return jpa.existsByEmail(email); }

    @Override
    public void deleteById(PersonId id) {
        jpa.deleteById(id.getValue());
    }
}
