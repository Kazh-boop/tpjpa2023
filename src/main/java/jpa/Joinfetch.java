package jpa;


import java.util.List;


import domain.animals.Animal;
import domain.persons.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;


public class Joinfetch {

    private EntityManager manager;

    public Joinfetch(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("withoutcreate");
        EntityManager manager = factory.createEntityManager();
        Joinfetch test = new Joinfetch(manager);


        TypedQuery<Owner> q = test.manager.createQuery("select distinct o from Owner o join fetch o.animals a",Owner.class);
        long start = System.currentTimeMillis();
        List<Owner> res = q.getResultList();

        for (Owner o : res){
            for (Animal a : o.getAnimals()){
                a.getName();
            }
        }

        long end = System.currentTimeMillis();
        long duree = end - start;
        System.err.println("temps d'exec = " +  duree);

        // TODO persist entity


        // TODO run request

        System.out.println(".. done");
    }

}