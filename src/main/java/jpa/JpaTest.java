package jpa;

import domain.Appointment;
import domain.animals.Animal;
import domain.animals.Cat;
import domain.animals.Dog;
import domain.persons.Owner;
import domain.persons.Veterinarian;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.Timestamp;
import java.util.List;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			// TODO create and persist entity
			test.createAnimals();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		test.listAnimalsByOwner("Baptiste");
		test.listAppointmentsByVet("Dr. House");
		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

	public void createAnimals() {
		int numOfAnimals = manager.createQuery("Select a From Animal a", Animal.class).getResultList().size();
		if (numOfAnimals == 0) {
			Owner baptiste = new Owner("Baptiste");
			Owner eddy = new Owner("Eddy");

			Animal kitty = new Cat("Kitty", 5, eddy);
			Animal newt = new Cat("Newt", 5, baptiste);
			Animal pharos = new Dog("Pharos", 2, baptiste);

			manager.persist(baptiste);
			manager.persist(eddy);

			manager.persist(kitty);
			manager.persist(newt);
			manager.persist(pharos);

			Veterinarian vet = new Veterinarian("Dr. House");
			manager.persist(vet);

			Appointment meetingBaptiste = new Appointment(baptiste, vet, new Timestamp(2023-1900, 10-1, 5, 14, 0, 0, 0));
			Appointment meetingEddy = new Appointment(eddy, vet, new Timestamp(2023-1900, 10-1, 5, 15, 0, 0, 0));
			manager.persist(meetingBaptiste);
			manager.persist(meetingEddy);
		}
	}

	public void createAnimalsByAddList() {
		int numOfAnimals = manager.createQuery("Select a From Animal a", Animal.class).getResultList().size();
		if (numOfAnimals == 0) {
			Owner baptiste = new Owner("Baptiste");
			Owner eddy = new Owner("Eddy");

			Animal kitty = new Cat("Kitty", 5);
			Animal newt = new Cat("Newt", 5);
			Animal pharos = new Dog("Pharos", 2);

			baptiste.addAnimal(newt);
			baptiste.addAnimal(pharos);
			eddy.addAnimal(kitty);

			manager.persist(baptiste);
			manager.persist(eddy);

			Veterinarian vet = new Veterinarian("Dr. House");
			manager.persist(vet);

			Appointment meetingBaptiste = new Appointment(baptiste, vet, new Timestamp(2023-1900, 10-1, 5, 14, 0, 0, 0));
			Appointment meetingEddy = new Appointment(eddy, vet, new Timestamp(2023-1900, 10-1, 5, 15, 0, 0, 0));
			manager.persist(meetingBaptiste);
			manager.persist(meetingEddy);
		}
	}

	public void listAnimalsByOwner(String ownerName) {
		List<Animal> resultList = manager.createQuery("Select a From Animal a where a.owner.name = :ownerName", Animal.class)
				.setParameter("ownerName", ownerName).getResultList();
		System.out.println("Owner " + ownerName + " has " + resultList.size() + " animals : ");
		for (Animal animal : resultList) {
			System.out.println(animal);
		}
	}

	public void listAppointmentsByVet(String vetName) {
		List<Appointment> resultList = manager.createQuery("Select m From Appointment m where m.veterinarian.name = :vetName", Appointment.class)
				.setParameter("vetName", vetName).getResultList();
		System.out.println("Veterinarian : " + vetName + " has " + resultList.size() + " appointments : ");
		for (Appointment meeting : resultList) {
			System.out.println(meeting);
		}
	}
}
