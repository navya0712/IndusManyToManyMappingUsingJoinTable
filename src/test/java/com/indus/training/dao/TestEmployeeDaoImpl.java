package com.indus.training.dao;

import com.indus.training.persist.entity.Employee;
import com.indus.training.persist.exceptions.EmployeeDaoException;
import com.indus.training.persist.impl.EmployeeDaoImpl;

import junit.framework.TestCase;

public class TestEmployeeDaoImpl extends TestCase {

	private EmployeeDaoImpl employeeDao = null;

	protected void setUp() throws Exception {
		employeeDao = new EmployeeDaoImpl();
	}

	protected void tearDown() throws Exception {
		employeeDao = null;
	}

	public void testInsertEmployee() {
		try {
			Employee employee = new Employee();
			employee.setFirstName("Navya");
			employee.setLastName("Bade");
			boolean result = employeeDao.insertEmployee(employee);
			assertTrue(result);
			Employee fetchedEmployee = employeeDao.fetchEmployeeById(employee.getEmployeeId());
			assertNotNull(fetchedEmployee);
			assertEquals("Navya", fetchedEmployee.getFirstName());
			assertEquals("Bade", fetchedEmployee.getLastName());
		} catch (EmployeeDaoException e) {
			fail("Exception thrown during insertion: " + e.getMessage());
		}
	}

	public void testFetchEmployeeById() {
		try {
			Employee employee = new Employee();
			employee.setFirstName("Navya");
			employee.setLastName("Bade");
			employeeDao.insertEmployee(employee);

			Employee fetchedEmployee = null;
			fetchedEmployee = employeeDao.fetchEmployeeById(employee.getEmployeeId());
			assertNotNull(fetchedEmployee);
			assertEquals(employee.getFirstName(), fetchedEmployee.getFirstName());
			assertEquals(employee.getLastName(), fetchedEmployee.getLastName());
		} catch (EmployeeDaoException e) {
			fail("Exception thrown during fetch: " + e.getMessage());
		}
	}

	public void testUpdateEmployeeById() {
		Employee employee = new Employee();
		employee.setFirstName("Teja");
		employee.setLastName("Bade");

		try {
			// Insert the employee
			boolean insertStatus = employeeDao.insertEmployee(employee);
			assertTrue("Employee insertion failed", insertStatus);
			employee.setFirstName("Teja Updated");
			employee.setLastName("Bade Updated");
			boolean updateStatus = employeeDao.updateEmployeeById(employee.getEmployeeId(), employee);
			assertTrue("Employee update failed", updateStatus);
			Employee fetchedEmployee = employeeDao.fetchEmployeeById(employee.getEmployeeId());
			assertNotNull("Fetched employee is null", fetchedEmployee);
			assertEquals("First name was not updated correctly", "Teja Updated", fetchedEmployee.getFirstName());
			assertEquals("Last name was not updated correctly", "Bade Updated", fetchedEmployee.getLastName());
		} catch (EmployeeDaoException e) {
			fail("Exception thrown during update: " + e.getMessage());
		}
	}

	public void testDeleteEmployeeById() {
		Employee employee = new Employee();
	    employee.setFirstName("Dhruthi");
	    employee.setLastName("Bade");

	    try {
	        boolean insertStatus = employeeDao.insertEmployee(employee);
	        assertTrue("Employee insertion failed", insertStatus);
	        Employee insertedEmployee = employeeDao.fetchEmployeeById(employee.getEmployeeId());
	        assertNotNull("Employee should be inserted", insertedEmployee);
	        boolean deleteStatus = employeeDao.deleteEmployeeById(employee.getEmployeeId());
	        assertTrue("Employee deletion failed", deleteStatus);
	        Employee deletedEmployee = employeeDao.fetchEmployeeById(employee.getEmployeeId());
	        assertNull("Employee should be deleted", deletedEmployee);
	    } catch (EmployeeDaoException e) {
	        fail("Exception thrown during delete: " + e.getMessage());
	    }
	}

}
