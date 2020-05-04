package com.diallo.poo.App3tierP2.Dao;

import java.util.List;
import java.util.UUID;

import com.diallo.poo.App3tierP2.Entities.Book;
import com.diallo.poo.App3tierP2.Entities.Person;

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
	public void update(UUID id);
	
	//Create operation
	public void save(Person p);
	
	// book
	public Book findBook(Book b);
	public List<Book> findBy(UUID id, String name);


}
