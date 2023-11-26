package dao.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.util.List;

public class EntityManagerHelper {

	private static final EntityManagerFactory emf;
	private static final ThreadLocal<EntityManager> threadLocal;

	static {
		emf = Persistence.createEntityManagerFactory("dev");
		threadLocal = new ThreadLocal<EntityManager>();
	}

	public static EntityManager getEntityManager() {
		EntityManager em = threadLocal.get();

		if (em == null) {
			em = emf.createEntityManager();
			threadLocal.set(em);
		}
		return em;
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocal.get();
		if (em != null) {
			em.close();
			threadLocal.set(null);
		}
	}

	public static void closeEntityManagerFactory() {
		emf.close();
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public static void commit() {
		getEntityManager().getTransaction().commit();
	}

	public abstract static class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

		private Class<T> clazz;

		protected EntityManager entityManager;

		public AbstractJpaDao() {
			this.entityManager = getEntityManager();
		}

		public void setClazz(Class<T> clazzToSet) {
			this.clazz = clazzToSet;
		}

		public T findOne(K id) {
			return entityManager.find(clazz, id);
		}

		public List<T> findAll() {
			return entityManager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();
		}

		public void save(T entity) {
			EntityTransaction t = this.entityManager.getTransaction();
			t.begin();
			entityManager.persist(entity);
			t.commit();

		}

		public T update(final T entity) {
			EntityTransaction t = this.entityManager.getTransaction();
			t.begin();
			T res = entityManager.merge(entity);
			t.commit();
			return res;

		}

		public void delete(T entity) {
			EntityTransaction t = this.entityManager.getTransaction();
			t.begin();
			entityManager.remove(entity);
			t.commit();

		}

		public void deleteById(K entityId) {
			T entity = findOne(entityId);
			delete(entity);
		}
	}
}