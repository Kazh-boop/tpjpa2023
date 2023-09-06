package jpa;

import domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
			Employee maverick = new Employee("Maverick");
			Employee goose = new Employee("Goose");
			manager.persist(maverick);
			manager.persist(goose);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
   	 	manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}
}
