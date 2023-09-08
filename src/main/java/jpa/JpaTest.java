package jpa;

import domain.Appointement;
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
		test.listMeetingsByVet("Dr. House");
		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

	public void createAnimals() {
		int numOfAnimals = manager.createQuery("Select a From Animal a", Animal.class).getResultList().size();
		if (numOfAnimals == 0) {
			Owner baptiste = new Owner("Baptiste");
			Owner eddy = new Owner("Eddy");

			Animal kitty = new Cat("Kitty", eddy);
			kitty.setSpecies("European");
			Animal newt = new Cat("Newt", baptiste);
			newt.setSpecies("European");
			Animal pharos = new Dog("Pharos", baptiste);
			pharos.setSpecies("Staff");

			manager.persist(baptiste);
			manager.persist(eddy);

			manager.persist(kitty);
			manager.persist(newt);
			manager.persist(pharos);

			Veterinarian vet = new Veterinarian("Dr. House");
			manager.persist(vet);

			Appointement meetingBaptiste = new Appointement(baptiste, vet, new Timestamp(2023-1900, 10-1, 5, 14, 0, 0, 0));
			Appointement meetingEddy = new Appointement(eddy, vet, new Timestamp(2023-1900, 10-1, 5, 15, 0, 0, 0));
			manager.persist(meetingBaptiste);
			manager.persist(meetingEddy);
		}
	}

	public void listAnimals() {
		manager.createQuery("Select a From Animal a", Animal.class).getResultList().forEach(System.out::println);
	}

	public void listAnimalsByOwner(String ownerName) {
		List<Animal> resultList = manager.createQuery("Select a From Animal a where a.owner.name = :ownerName", Animal.class)
				.setParameter("ownerName", ownerName).getResultList();
		System.out.println("Owner : " + ownerName + " has " + resultList.size() + " animals : ");
		for (Animal animal : resultList) {
			System.out.println(animal);
		}
	}

	public void listMeetings() {
		manager.createQuery("Select m From Appointement m", Appointement.class).getResultList().forEach(System.out::println);
	}

	public void listMeetingsByVet(String vetName) {
		List<Appointement> resultList = manager.createQuery("Select m From Appointement m where m.veterinarian.name = :vetName", Appointement.class)
				.setParameter("vetName", vetName).getResultList();
		System.out.println("Veterinarian : " + vetName + " has " + resultList.size() + " meetings : ");
		for (Appointement meeting : resultList) {
			System.out.println(meeting);
		}
	}

	public void listMeetingsByOwner(String ownerName) {
		List<Appointement> resultList = manager.createQuery("Select m From Appointement m where m.owner.name = :ownerName", Appointement.class)
				.setParameter("ownerName", ownerName).getResultList();
		System.out.println("Owner : " + ownerName + " has " + resultList.size() + " meetings : ");
		for (Appointement meeting : resultList) {
			System.out.println(meeting);
		}
	}


}
