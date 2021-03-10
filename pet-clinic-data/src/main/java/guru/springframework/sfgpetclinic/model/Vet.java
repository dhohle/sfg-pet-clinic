package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Vet extends Person {


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();

    @Builder(builderMethodName = "VetBuilder")
    public Vet(Set<Specialty> specialties, Long id, String firstName, String lastName){
        super(id, firstName, lastName);
        this.specialties = specialties;
    }

}
