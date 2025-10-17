
package com.clinichub.adapters.web.sharedkernel.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.clinichub.adapters.persistence.sharedkernel.jpa.PersonSpringRepository;
import com.clinichub.adapters.web.sharedkernel.dto.*;
import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.domain.Person;
import com.clinichub.core.sharedkernel.usecase.*;
import com.clinichub.shared.mappers.PersonDtoMapper;

@RestController
@RequestMapping("/api/persons")
@Validated
public class PersonController {

    private final CreatePerson createUC;
    private final UpdatePerson updateUC;
    private final GetPerson getUC;
    private final ListPersons listUC;
    private final DeletePerson deleteUC;

    private final PersonSpringRepository personRepo; 
    private final PersonDtoMapper mapper;

    public PersonController(
        CreatePerson createUC,
        UpdatePerson updateUC,
        GetPerson getUC,
        ListPersons listUC,
        DeletePerson deleteUC,
        PersonSpringRepository personRepo,
        PersonDtoMapper mapper
    ) {
        this.createUC = createUC;
        this.updateUC = updateUC;
        this.getUC = getUC;
        this.listUC = listUC;
        this.deleteUC = deleteUC;
        this.personRepo = personRepo;
        this.mapper = mapper;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@RequestBody CreatePersonDto body) {
        Person p = createUC.handle(mapper.toCreateCommand(body));
        return mapper.enrich(p); 
    }


    @GetMapping("/{id}")
    public PersonDto get(@PathVariable Long id) {
        return personRepo.fetchOneView(id)
                .map(mapper::fromView)
                .orElseGet(() -> mapper.enrich(getUC.handle(new PersonId(id))));
    }


    @GetMapping
    public List<PersonDto> list() {
        var lista = personRepo.fetchAll();
        return lista.stream().map(mapper::fromView).toList();
    }


    @PatchMapping("/{id}")
    public PersonDto update(@PathVariable Long id, @RequestBody UpdatePersonDto body) {
        Person p = updateUC.handle(mapper.toUpdateCommand(id, body));
        return mapper.enrich(p);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteUC.handle(new PersonId(id));
    }
}
