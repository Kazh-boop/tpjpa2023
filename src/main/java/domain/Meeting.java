package domain;

import domain.persons.Owner;
import domain.persons.Veterinarian;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Meeting {
    private Long id;

    private Owner owner;

    private Veterinarian veterinarian;

    private Timestamp timestamp;

    public Meeting() {
    }

    public Meeting(Owner owner, Veterinarian vet, Timestamp timestamp) {
        this.owner = owner;
        this.veterinarian = vet;
        this.timestamp = timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @ManyToOne
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @ManyToOne
    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian vet) {
        this.veterinarian = vet;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return "Meeting between " + owner.getName() + " and " + veterinarian.getName() + " at " + timestamp;
    }
}
