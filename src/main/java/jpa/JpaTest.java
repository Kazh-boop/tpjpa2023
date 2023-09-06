package jpa;

import domain.Department;
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
			test.createEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
   	 	manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

	public void createEmployees() {
		int numOfEmployees = manager.createQuery("Select a From Employee a", Employee.class).getResultList().size();
		if (numOfEmployees == 0) {
			Department topGunDepartment = new Department("Top Gun");
			manager.persist(topGunDepartment);
			manager.persist(new Employee("Maverick", topGunDepartment));
			manager.persist(new Employee("Goose", topGunDepartment));

			Department isticDepartment = new Department("ISTIC");
			manager.persist(isticDepartment);
			manager.persist(new Employee("Eddy", isticDepartment));
			manager.persist(new Employee("Aymerick", isticDepartment));
		}
	}
}
