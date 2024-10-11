package com.indus.training.persist.impl;

import javax.persistence.EntityManager;

import com.indus.training.persist.dao.IEmployeeDao;
import com.indus.training.persist.entity.Employee;
import com.indus.training.persist.exceptions.EmployeeDaoException;
import com.indus.training.persist.util.EntityManagerUtil;

public class EmployeeDaoImpl implements IEmployeeDao {

	/**
	 * Inserts a new employee into the database.
	 * 
	 * @param empObj The Employee object to be inserted.
	 * @return true if the employee was successfully inserted, false otherwise.
	 * @throws EmployeeDaoException If there is an issue during the insertion.
	 */
	@Override
	public Boolean insertEmployee(Employee employee) throws EmployeeDaoException {
		if (employee == null) {
			throw new NullPointerException("Employee object cannot be null");
		}

		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(employee);
			em.getTransaction().commit();
			status = true;
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	/**
	 * Fetches an employee by their ID.
	 * 
	 * @param empId The ID of the employee to fetch.
	 * @return The Employee object corresponding to the given ID.
	 * @throws EmployeeDaoException If there is an issue during the fetch.
	 */
	@Override
	public Employee fetchEmployeeById(Integer employeeId) throws EmployeeDaoException {
		Employee employee = null;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			employee = em.find(Employee.class, employeeId);
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return employee;
	}

	/**
	 * Updates the details of an employee by their ID.
	 * 
	 * @param empId    The ID of the employee to update.
	 * @param employee The details of the employee.
	 * @return true if the update was successful, false otherwise.
	 * @throws EmployeeDaoException If there is an issue during the update.
	 */
	@Override
	public Boolean updateEmployeeById(Integer employeeId, Employee employee) throws EmployeeDaoException {
		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Employee empObj = em.find(Employee.class, employeeId);
			if (empObj != null) {
				empObj.setFirstName(employee.getFirstName());
				empObj.setLastName(employee.getLastName());
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
	 * Deletes an employee by their ID.
	 * 
	 * @param empId The ID of the employee to delete.
	 * @return true if the deletion was successful, false otherwise.
	 * @throws EmployeeDaoException If there is an issue during the deletion.
	 */
	@Override
	public Boolean deleteEmployeeById(Integer employeeId) throws EmployeeDaoException {
		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Employee employee = em.find(Employee.class, employeeId);
			if (employee != null) {
				em.remove(employee);
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
