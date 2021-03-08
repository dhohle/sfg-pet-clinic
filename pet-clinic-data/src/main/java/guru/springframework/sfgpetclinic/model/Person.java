package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

// @MappedSuperClass tells that this class will not be mapped directly to the database, but is used by other classes
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
