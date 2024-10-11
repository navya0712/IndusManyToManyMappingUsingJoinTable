package com.indus.training.dao;

import com.indus.training.persist.entity.Employee;
import com.indus.training.persist.entity.Project;
import com.indus.training.persist.exceptions.EmployeeDaoException;
import com.indus.training.persist.exceptions.ProjectDaoException;
import com.indus.training.persist.impl.EmployeeDaoImpl;
import com.indus.training.persist.impl.ProjectDaoImpl;

import junit.framework.TestCase;

public class TestManyToManyRelationship extends TestCase {

	private EmployeeDaoImpl employeeDao;
	private ProjectDaoImpl projectDao;

	@Override
	protected void setUp() throws Exception {
		employeeDao = new EmployeeDaoImpl();
		projectDao = new ProjectDaoImpl();
	}

	@Override
	protected void tearDown() throws Exception {
		employeeDao = null;
		projectDao = null;
	}

	public void testManyToManyRelationship() {
		try {
		Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");

        // Create Project
        Project project = new Project();
        project.setTitle("Project A");

        // Establish relationship
        employee.getProjects().add(project);
        project.getEmployees().add(employee);

    
        employeeDao.insertEmployee(employee); // Use DAO for insertion
        projectDao.insertProject(project);   // Use DAO for insertion
     

        // Fetch and verify
        Employee fetchedEmployee = employeeDao.fetchEmployeeById(employee.getEmployeeId());
        Project fetchedProject = projectDao.fetchProjectById(project.getProjectId());

        assertTrue(fetchedEmployee.getProjects().contains(fetchedProject));
        assertTrue(fetchedProject.getEmployees().contains(fetchedEmployee));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
