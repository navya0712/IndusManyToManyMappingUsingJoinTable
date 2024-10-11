package com.indus.training.persist.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static final String PERSISTENCE_UNIT_NAME = "myPersistenceUnit";
	private static EntityManagerFactory emf;

	/**
	 * Initializes the EntityManagerFactory.
	 */
	static {
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Provides an EntityManager for database operations.
	 * 
	 * @return an EntityManager instance
	 */
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public static void closeEntityManagerFactory(EntityManagerFactory emf) {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}

}
