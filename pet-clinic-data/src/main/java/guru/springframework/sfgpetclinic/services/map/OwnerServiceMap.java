package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {

        if(object != null){
            if(object.getPets()!= null){
                object.getPets().forEach(pet->{
                    if(pet.getPetType()!=null){
                       pet.setPetType(petTypeService.save(pet.getPetType()));
                    }else{
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Owner findByFirstName(String firstname) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getFirstName().equalsIgnoreCase(firstname))
                .findFirst()
                .orElse(null);
    }

}
