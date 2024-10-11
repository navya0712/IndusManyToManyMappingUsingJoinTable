package com.indus.training.persist.impl;

import javax.persistence.EntityManager;

import com.indus.training.persist.dao.IProjectDao;
import com.indus.training.persist.entity.Employee;
import com.indus.training.persist.entity.Project;
import com.indus.training.persist.exceptions.ProjectDaoException;
import com.indus.training.persist.util.EntityManagerUtil;

public class ProjectDaoImpl implements IProjectDao {

	@Override
	public Boolean insertProject(Project project) throws ProjectDaoException {
		if (project == null) {
			throw new NullPointerException("Project object cannot be null");
		}

		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(project);
			em.getTransaction().commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	@Override
	public Project fetchProjectById(Integer projectId) throws ProjectDaoException {
		Project project = null;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			project = em.find(Project.class, projectId);
		} finally {
			closeEntityManager(em);
		}

		return project;
	}

	@Override
	public Boolean updateProjectById(Integer projectId, Project project) throws ProjectDaoException {
		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Project projectObj = em.find(Project.class, projectId);
			if (projectObj != null) {
				projectObj.setTitle(project.getTitle());
				em.getTransaction().commit();
				status = true;
			} else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	@Override
	public Boolean deleteProjectById(Integer projectId) throws ProjectDaoException {
		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Project project = em.find(Project.class, projectId);
			if (project != null) {
				em.remove(project);
				em.getTransaction().commit();
				status = true;
			} else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	/**
	 * Rolls back the transaction if an exception occurs.
	 * 
	 * @param em the EntityManager involved in the transaction
	 * @param e  the exception that occurred
	 */
	private void handleException(EntityManager em, Exception e) {
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
	}

	/**
	 * Closes the EntityManager.
	 * 
	 * @param em the EntityManager to be closed
	 */
	private void closeEntityManager(EntityManager em) {
		if (em != null) {
			em.close();
		}
	}

}
