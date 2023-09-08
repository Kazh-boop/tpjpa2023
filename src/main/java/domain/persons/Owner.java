package domain.persons;

import domain.Meeting;
import domain.animals.Animal;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner {

    private Long id;

    private String name;

    private List<Animal> animals = new ArrayList<Animal>();

    private List<Meeting> meetings = new ArrayList<Meeting>();

    public Owner() {}

    public Owner(String name) {
        this.name = name;
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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public String toString() {
        return "Owner: " + this.name;
    }
}
