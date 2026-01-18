package com.clinichub.adapters.persistence.reference.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountrySpringRepository extends JpaRepository<CountryJpa, Long> {
    Optional<CountryJpa> findByName(String name);
}





