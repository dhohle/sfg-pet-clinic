package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    private Long ownerId = 1L;
    private String firstName= "last name";
    private String lastName= "first name";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());

        ownerServiceMap.save(Owner.OwnerBuilder().id(ownerId).lastName(lastName).firstName(firstName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();;
        assertEquals(1, owners.size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.OwnerBuilder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner2);
        assertEquals(id, savedOwner.getId());
    }

    void saveNoId(){
        Owner savedOwner = ownerServiceMap.save(Owner.OwnerBuilder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        ownerServiceMap.findById(ownerId);
    }

    @Test
    void findByLastName() {
        Owner lastname = ownerServiceMap.findByLastName(lastName);
        assertNotNull(lastname);
        assertEquals(ownerId, lastname.getId());
    }
    @Test
    void findByLastNameNotFound() {
        Owner lastname = ownerServiceMap.findByLastName("No last name");
        assertNull(lastname);
    }

    @Test
    void findByFirstName() {
        Owner firstname = ownerServiceMap.findByFirstName(firstName);
        assertNotNull(firstname);
        assertEquals(ownerId, firstname.getId());
    }
    @Test
    void findByFirstNameNotFound() {
        Owner firstname = ownerServiceMap.findByFirstName("No first name");
        assertNull(firstname);
    }

}