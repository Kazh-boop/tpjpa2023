package domain.person;

import domain.Appointment;
import domain.animals.Animal;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner {

    private Long id;

    private String name;

    private List<Animal> animals = new ArrayList<Animal>();

    private List<Appointment> appointments = new ArrayList<Appointment>();

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
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String toString() {
        return "Owner: " + this.name;
    }
}
