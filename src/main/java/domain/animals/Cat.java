package domain.animals;

import domain.persons.Owner;
import jakarta.persistence.Entity;

@Entity
public class Cat extends Animal {

    public Cat() {
    }

    public Cat(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

    public Cat(String name) {
        this.name = name;
    }
}
