package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService {

    private final VetRepository repository;

    public VetSDJpaService(VetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Vet> findAll() {
        final Set<Vet> vets = new HashSet<>();
         this.repository.findAll().forEach(vets::add);
         return vets;
    }

    @Override
    public Vet findById(Long aLong) {
        return this.repository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return this.repository.save(object);
    }

    @Override
    public void delete(Vet object) {
        this.repository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        this.repository.deleteById(aLong);
    }
}
