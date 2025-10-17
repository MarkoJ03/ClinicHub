package com.clinichub.adapters.persistence.sharedkernel.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface PersonSpringRepository extends JpaRepository<PersonJpa, Long> {

    Optional<PersonJpa> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long excludeId);


    interface PersonView {
        Long getId();
        String getFirstName();
        String getLastName();
        String getEmail();
        String getPhone();
        String getStreet();
        String getNumber();
        String getZip();
        Long getCityId();
        String getCityName();
        Long getCountryId();
        String getCountryName();
    }

    interface CityCountryView {
        Long getCityId();
        String getCityName();
        Long getCountryId();
        String getCountryName();
    }

    @Query("""
        select p.id as id, p.firstName as firstName, p.lastName as lastName,
               p.email as email, p.phone as phone,
               p.address.street as street, p.address.number as number, p.address.zip as zip,
               c.id as cityId, c.name as cityName,
               co.id as countryId, co.name as countryName
        from PersonJpa p
        join p.address.city c
        join c.country co
        where p.id = :id
    """)
    Optional<PersonView> fetchOneView(@Param("id") Long id);

    @Query("""
        select p.id as id, p.firstName as firstName, p.lastName as lastName,
               p.email as email, p.phone as phone,
               p.address.street as street, p.address.number as number, p.address.zip as zip,
               c.id as cityId, c.name as cityName,
               co.id as countryId, co.name as countryName
        from PersonJpa p
        join p.address.city c
        join c.country co
    """)
    List<PersonView> fetchAll();

    @Query("""
        select c.id as cityId, c.name as cityName, co.id as countryId, co.name as countryName
        from CityJpa c join c.country co
        where c.id = :cityId
    """)
    Optional<CityCountryView> fetchCityCountry(@Param("cityId") Long cityId);
}
