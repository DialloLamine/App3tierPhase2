package com.diallo.poo.App3tierGit.metier;

import java.util.List;

import com.diallo.poo.App3tierGit.entities.Book;
import com.diallo.poo.App3tierGit.entities.Person;

public interface IMetier {
	
	//Data access needs
	public List<Person> getMembres();
	public void register(Person p);
	public void update(Person p);
	public void unregister(Person p);
	public List<Person> findByName(String name);
	
	//Business Logic
	public int computeRemainingDays(Book b);
	
	//...

}
