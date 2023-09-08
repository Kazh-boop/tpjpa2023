package domain.animals;

import domain.persons.Owner;
import jakarta.persistence.Entity;

@Entity
public class Dog extends Animal {

    public Dog() {
    }

    public Dog(String name, Owner owner) {
        this.setName(name);
        this.setOwner(owner);
    }

    public Dog(String name) {
        this.setName(name);
    }
}
