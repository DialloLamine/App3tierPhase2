package com.diallo.poo.App3tierP2.Metier;

import java.util.List;

import com.diallo.poo.App3tierP2.Entities.Book;
import com.diallo.poo.App3tierP2.Entities.Person;

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

	//Data access needs a implementer dans la semaine du 04 Mai 20
		public List<Book> getBooks();
		public void register(Book b);
		public void update(Book b);
		public void unregister(Book b);
		
		
		
}
