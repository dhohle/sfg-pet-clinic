package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(final String lastName);

    List<Owner> findAllByLastNameLike(final String lastName);

    Owner findByFirstName(String lastName);
}
