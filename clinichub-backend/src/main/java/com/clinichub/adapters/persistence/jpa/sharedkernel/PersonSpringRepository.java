package com.clinichub.adapters.persistence.jpa.sharedkernel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonSpringRepository extends JpaRepository<PersonJpa, Long>{
    Optional<PersonJpa> findByEmail(String email);
    boolean existsByEmail(String email);
}
