package domain.animals;

import domain.persons.Owner;
import jakarta.persistence.*;

@Entity
public abstract class Animal {

    private Long id;

    protected String name;

    protected int age;

    protected Owner owner;

    public Animal() {
    }

    protected Animal(String name, int age, Owner owner) {
        this.name = name;
        this.age = age;
        this.owner = owner;
    }

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ManyToOne
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String toString() {
        return this.name + "is " + this.age + " years old " + getClass().getSimpleName().toLowerCase();
    }
}
