package cranfield.group.project.airfoil.server.services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactoryProvider {

	private static final String PERSISTENCE_UNIT_NAME = "PU";
	private static final EntityFactoryProvider SINGLETON = new EntityFactoryProvider();

	private EntityManagerFactory emf;

	private EntityFactoryProvider() {
	}

	public static EntityFactoryProvider getInstance(){
		return SINGLETON;
	}

	public EntityManagerFactory createEntityManagerFactory(){
		if (emf == null)
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		return emf;
	}

	public void close() {
		if (emf != null && emf.isOpen())
			emf.close();
		emf = null;
	}
}
