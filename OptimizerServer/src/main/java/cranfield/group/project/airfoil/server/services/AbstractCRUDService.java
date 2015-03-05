package cranfield.group.project.airfoil.server.services;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import cranfield.group.project.airfoil.server.entities.AbstractEntityObject;


public abstract class AbstractCRUDService<K extends Serializable, T extends AbstractEntityObject<K, T>> {

	private final static EntityManagerFactory emf;
	private final Class<T> entityClass;

	static {
		emf = EntityFactoryProvider.getInstance().createEntityManagerFactory();
	}

	public T find(K id) {
		EntityManager em = emf.createEntityManager();
		T obj = em.find(entityClass, id);
		em.close();
		return obj;
	}

	public T persist(T entity){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
                return entity;
	}

	public AbstractCRUDService() {
		entityClass = resolveEntityClass();
	}

	@SuppressWarnings("unchecked")
	private Class<T> resolveEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}
}
