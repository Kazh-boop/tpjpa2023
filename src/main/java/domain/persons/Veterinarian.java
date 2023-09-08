package domain.persons;

import domain.Appointement;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Veterinarian {
    private Long id;

    private String name;

    private List<Appointement> meetings = new ArrayList<Appointement>();

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
    public List<Appointement> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Appointement> meetings) {
        this.meetings = meetings;
    }
}
