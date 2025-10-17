package com.clinichub.adapters.persistence.reference.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitySpringRepository extends JpaRepository<CityJpa, Long> {
    List<CityJpa> findByCountryId(Long countryId);
    Optional<CityJpa> findByNameAndCountryId(String name, Long countryId);
}



