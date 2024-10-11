package com.indus.training.dao;

import com.indus.training.persist.entity.Project;
import com.indus.training.persist.exceptions.ProjectDaoException;
import com.indus.training.persist.impl.ProjectDaoImpl;

import junit.framework.TestCase;

public class TestProjectDaoImpl extends TestCase {
	private ProjectDaoImpl projectDao = null;

	@Override
	protected void setUp() throws Exception {
		projectDao = new ProjectDaoImpl();
	}

	@Override
	protected void tearDown() throws Exception {
		projectDao = null;
	}

	public void testInsertProject() {
		try {
			Project project = new Project();
			project.setTitle("Project X");
			boolean result = projectDao.insertProject(project);
			assertTrue(result);
			Project fetchedProject = projectDao.fetchProjectById(project.getProjectId());
			assertNotNull(fetchedProject);
			assertEquals("Project X", fetchedProject.getTitle());
		} catch (ProjectDaoException e) {
			fail("Exception thrown during insertion: " + e.getMessage());
		}
	}

	public void testFetchProjectById() {
		try {
			Project project = new Project();
			project.setTitle("Project Y");
			projectDao.insertProject(project);

			Project fetchedProject = projectDao.fetchProjectById(project.getProjectId());
			assertNotNull(fetchedProject);
			assertEquals(project.getTitle(), fetchedProject.getTitle());
		} catch (ProjectDaoException e) {
			fail("Exception thrown during fetch: " + e.getMessage());
		}
	}

	public void testUpdateProjectById() {
		Project project = new Project();
		project.setTitle("Project Z");

		try {
			boolean insertStatus = projectDao.insertProject(project);
			assertTrue("Project insertion failed", insertStatus);
			project.setTitle("Project Z Updated");
			boolean updateStatus = projectDao.updateProjectById(project.getProjectId(), project);
			assertTrue("Project update failed", updateStatus);
			Project fetchedProject = projectDao.fetchProjectById(project.getProjectId());
			assertNotNull("Fetched project is null", fetchedProject);
			assertEquals("Project title was not updated correctly", "Project Z Updated", fetchedProject.getTitle());
		} catch (ProjectDaoException e) {
			fail("Exception thrown during update: " + e.getMessage());
		}
	}

	public void testDeleteProjectById() {
		Project project = new Project();
		project.setTitle("Project A");

		try {

			boolean insertStatus = projectDao.insertProject(project);
			assertTrue("Project insertion failed", insertStatus);
			Project insertedProject = projectDao.fetchProjectById(project.getProjectId());
			assertNotNull("Project should be inserted", insertedProject);
			boolean deleteStatus = projectDao.deleteProjectById(project.getProjectId());
			assertTrue("Project deletion failed", deleteStatus);
			Project deletedProject = projectDao.fetchProjectById(project.getProjectId());
			assertNull("Project should be deleted", deletedProject);
		} catch (ProjectDaoException e) {
			fail("Exception thrown during delete: " + e.getMessage());
		}
	}

}
