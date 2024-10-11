package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Project;
import com.indus.training.persist.exceptions.ProjectDaoException;

/**
 * Interface for Employee Data Access Object (DAO). It provides methods for
 * performing CRUD operations on Employee data.
 */
public interface IProjectDao {
	/**
	 * Inserts a new project into the database.
	 * 
	 * @param project The Project object to be inserted.
	 * @return true if the project was successfully inserted, false otherwise.
	 * @throws ProjectDaoException If there is an issue during the insertion.
	 */
	public Boolean insertProject(Project project) throws ProjectDaoException;

	/**
	 * Fetches an employee by their ID.
	 * 
	 * @param project Id The ID of the project to fetch.
	 * @return The Project object corresponding to the given ID.
	 * @throws ProjectDaoException If there is an issue during the fetch.
	 */
	public Project fetchProjectById(Integer projectId) throws ProjectDaoException;

	/**
	 * Updates the Project Details of an project by their ID.
	 * 
	 * @param projectId The ID of the project to update.
	 * @param project   the project details to be updated
	 * @return true if the update was successful, false otherwise.
	 * @throws ProjectDaoException If there is an issue during the update.
	 */
	public Boolean updateProjectById(Integer projectId, Project project) throws ProjectDaoException;

	/**
	 * Deletes an project by their ID.
	 * 
	 * @param projectId The ID of the project to delete.
	 * @return true if the deletion was successful, false otherwise.
	 * @throws ProjectDaoException If there is an issue during the deletion.
	 */
	public Boolean deleteProjectById(Integer projectId) throws ProjectDaoException;

}
