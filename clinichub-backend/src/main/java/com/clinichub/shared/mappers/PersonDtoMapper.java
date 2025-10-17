// shared/mappers/PersonDtoMapper.java
package com.clinichub.shared.mappers;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.clinichub.adapters.persistence.sharedkernel.jpa.PersonSpringRepository;
import com.clinichub.adapters.persistence.sharedkernel.jpa.PersonSpringRepository.CityCountryView;
import com.clinichub.adapters.persistence.sharedkernel.jpa.PersonSpringRepository.PersonView;
import com.clinichub.adapters.web.sharedkernel.dto.*;
import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.domain.Person;
import com.clinichub.core.sharedkernel.usecase.CreatePerson;
import com.clinichub.core.sharedkernel.usecase.UpdatePerson;

@Component
public class PersonDtoMapper {

    private final PersonSpringRepository personRepo;

    public PersonDtoMapper(PersonSpringRepository personRepo) {
        this.personRepo = personRepo;
    }

    // WRITE (create)
    public CreatePerson.Command toCreateCommand(CreatePersonDto dto) {
        return new CreatePerson.Command(
            dto.firstName(),
            dto.lastName(),
            dto.email(),
            dto.phone(),
            dto.street(),
            dto.number(),
            dto.zip(),
            dto.cityId() == null ? null : new CityId(dto.cityId())
        );
    }

    // WRITE (update)
    public UpdatePerson.Command toUpdateCommand(Long id, UpdatePersonDto dto) {
        return new UpdatePerson.Command(
            new PersonId(id),
            Optional.ofNullable(dto.firstName()),
            Optional.ofNullable(dto.lastName()),
            Optional.ofNullable(dto.email()),
            Optional.ofNullable(dto.phone()),
            Optional.ofNullable(dto.street()),
            Optional.ofNullable(dto.number()),
            Optional.ofNullable(dto.zip()),
            Optional.ofNullable(dto.cityId() == null ? null : new CityId(dto.cityId()))
        );
    }

    // READ iz projekcije
    public PersonDto fromView(PersonView v) {
        return new PersonDto(
            v.getId(),
            v.getFirstName(),
            v.getLastName(),
            v.getEmail(),
            v.getPhone(),
            new AddressDTO(
                v.getStreet(),
                v.getNumber(),
                v.getZip(),
                v.getCityId(),
                v.getCityName(),
                v.getCountryId(),
                v.getCountryName()
            )
        );
    }

    // READ iz domena (fallback obogaćenje)
    public PersonDto enrich(Person p) {
        Long cityId = (p.getAddress() != null && p.getAddress().getCityId() != null)
            ? p.getAddress().getCityId().getValue() : null;

        String cityName = null;
        Long countryId = null;
        String countryName = null;

        if (cityId != null) {
            Optional<CityCountryView> cc = personRepo.fetchCityCountry(cityId);
            if (cc.isPresent()) {
                cityName = cc.get().getCityName();
                countryId = cc.get().getCountryId();
                countryName = cc.get().getCountryName();
            }
        }

        return new PersonDto(
            p.getId() == null ? null : p.getId().getValue(),
            p.getFirstName(),
            p.getLastName(),
            p.getEmail(),
            p.getPhone(),
            new AddressDTO(
                p.getAddress() != null ? p.getAddress().getStreet() : null,
                p.getAddress() != null ? p.getAddress().getNumber() : null,
                p.getAddress() != null ? p.getAddress().getZip() : null,
                cityId,
                cityName,
                countryId,
                countryName
            )
        );
    }
}
