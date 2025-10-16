package com.clinichub.core.sharedkernel.usecase;

import java.util.Optional;

import com.clinichub.core.shared.types.CityId;
import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.domain.Address;
import com.clinichub.core.sharedkernel.domain.Person;
import com.clinichub.core.sharedkernel.port.PersonRepository;

public class UpdatePerson {

	public record Command(
            PersonId personId,
            Optional<String> firstName,
            Optional<String> lastName,
            Optional<String> email,
            Optional<String> phone,
            Optional<String> street,
            Optional<String> number,
            Optional<String> zip,
            Optional<CityId> cityId
    ) {}

	    private final PersonRepository repo;
	
	    public UpdatePerson(PersonRepository repo) {
	        this.repo = repo;
	    }

    public Person handle(Command cmd) {
        Person p = repo.findById(cmd.personId())
                .orElseThrow(() -> new IllegalArgumentException("Person not found"));

        cmd.firstName().ifPresent(p::changeFirstName);
        cmd.lastName().ifPresent(p::changeLastName);
        cmd.email().ifPresent(v -> {
            if (repo.existsByEmail(v) && !v.equalsIgnoreCase(p.getEmail()))
                throw new IllegalStateException("Email already in use");
            p.changeEmail(v);
        });
        cmd.phone().ifPresent(p::changePhone);

        if (cmd.street().isPresent() || cmd.number().isPresent() || cmd.zip().isPresent() || cmd.cityId().isPresent()) {
            Address a = p.getAddress();
            String street  = cmd.street().orElse(a.getStreet());
            String number  = cmd.number().orElse(a.getNumber());
            String zip     = cmd.zip().orElse(a.getZip());
            CityId cityId  = cmd.cityId().orElse(a.getCityId());
            p.changeAddress(new Address(street, number, zip, cityId));
        }

        return repo.save(p);
    }
}
