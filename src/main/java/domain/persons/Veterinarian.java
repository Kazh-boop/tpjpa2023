package domain.persons;

import domain.Meeting;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
public class Veterinarian {
    private Long id;

    private String name;

    private List<Meeting> meetings = new ArrayList<Meeting>();

    public Veterinarian() {
    }

    public Veterinarian(String name) {
        this.setName(name);
    }

    public Veterinarian(String name, Long id) {
        this.setName(name);
        this.setId(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @OneToMany(mappedBy = "veterinarian", cascade = CascadeType.PERSIST)
    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}
