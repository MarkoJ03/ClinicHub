//package com.clinichub.adapters.web.sharedkernel.controllers;
//
//import java.util.List;
//
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.clinichub.adapters.web.sharedkernel.dto.CreatePersonDto;
//import com.clinichub.adapters.web.sharedkernel.dto.PersonDto;
//import com.clinichub.adapters.web.sharedkernel.dto.UpdatePersonDto;
//import com.clinichub.core.sharedkernel.domain.Person;
//import com.clinichub.core.sharedkernel.usecase.CreatePerson;
//import com.clinichub.core.sharedkernel.usecase.GetPerson;
//import com.clinichub.core.sharedkernel.usecase.ListPersons;
//import com.clinichub.core.sharedkernel.usecase.UpdatePerson;
//import com.clinichub.shared.mappers.PersonDtoMapper;
//
//@RestController
//@RequestMapping("/api/persons")
//@Validated
//public class PersonController {
//
//    private final CreatePerson create;
//    private final UpdatePerson update;
//    private final GetPerson get;
//    private final ListPersons list;
//
//    public PersonController(CreatePerson create, UpdatePerson update, GetPerson get, ListPersons list) {
//        this.create = create;
//        this.update = update;
//        this.get = get;
//        this.list = list;
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public PersonDto create(@RequestBody @Validated CreatePersonDto body){
//        Person p = create.handle(new CreatePerson.Command(
//                body.firstName(), body.lastName(), body.email(), body.phone(),
//                body.street(), body.number(), body.zip(),
//                body.cityId()==null? null : new CityId(body.cityId())
//        ));
//        return PersonDtoMapper.toDto(p);
//    }
//
//    @GetMapping("/{id}")
//    public PersonDto get(@PathVariable Long id){
//        Person p = get.handle(PersonDtoMapper.idOf(id));
//        return PersonDtoMapper.toDto(p);
//    }
//
//    @GetMapping
//    public List<PersonDto> list(@RequestParam(defaultValue="0") int offset,
//                                @RequestParam(defaultValue="20") int limit){
//        return list.handle(offset, Math.max(1, limit))
//                   .stream().map(PersonDtoMapper::toDto).toList();
//    }
//
//    @PatchMapping("/{id}")
//    public PersonDto update(@PathVariable Long id, @RequestBody UpdatePersonDto body){
//        UpdatePerson.Command cmd = new UpdatePerson.Command(
//                new PersonId(id),
//                Optional.ofNullable(body.firstName()),
//                Optional.ofNullable(body.lastName()),
//                Optional.ofNullable(body.email()),
//                Optional.ofNullable(body.phone()),
//                Optional.ofNullable(body.street()),
//                Optional.ofNullable(body.number()),
//                Optional.ofNullable(body.zip()),
//                Optional.ofNullable(body.cityId()==null? null : new CityId(body.cityId()))
//        );
//        Person p = update.handle(cmd);
//        return PersonDtoMapper.toDto(p);
//    }
//}