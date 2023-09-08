package domain.animals;

import domain.persons.Owner;
import jakarta.persistence.*;

@Entity
public abstract class Animal {

    private Long id;

    protected String name;

    protected Owner owner;

    protected String race;

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

    @ManyToOne
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Transient
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String toString() {
        return "Animal name : " + this.name;
    }
}
