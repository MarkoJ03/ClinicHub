package com.clinichub.core.sharedkernel.usecase;

import com.clinichub.core.shared.types.PersonId;
import com.clinichub.core.sharedkernel.port.PersonRepository;

public class DeletePerson {
    private final PersonRepository repo;
    public DeletePerson(PersonRepository repo) { this.repo = repo; }

    public void handle(PersonId id) {
        repo.deleteById(id);
    }
}