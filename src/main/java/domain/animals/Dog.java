package domain.animals;

import domain.persons.Owner;
import jakarta.persistence.Entity;

@Entity
public class Dog extends Animal {

    public Dog() {
    }

    public Dog(String name, int age, Owner owner) {
        super(name, age, owner);
    }

    public Dog(String name, int age) {
        super(name, age);
    }
}
