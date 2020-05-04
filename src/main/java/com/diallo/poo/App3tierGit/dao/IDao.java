package com.diallo.poo.App3tierGit.dao;

import java.util.List;
import java.util.UUID;

import com.diallo.poo.App3tierGit.entities.Person;

/**
 * CRUD operations
 * 
 * @author user
 *
 */
public interface IDao {
	//Read operations
	public List<Person> findAll();
	public Person findById(UUID id);
	public List<Person> findBy(String property, String value);
	
	//Delete operation
	public void delete(UUID id);
	public void delete(Person p);
	
	//Update operation
	public void update(Person p);
	
	//Create operation
	public void save(Person p);
}
