package domain.animals;

import domain.persons.Owner;
import jakarta.persistence.Entity;

@Entity
public class Cat extends Animal {

    public Cat() {
    }

    public Cat(String name, int age, Owner owner) {
        super(name, age, owner);
    }

    public Cat(String name, int age) {
        super(name, age);
    }
}
