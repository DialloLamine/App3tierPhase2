package com.diallo.poo.App3tierP2.Metier;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.diallo.poo.App3tierP2.Dao.IDao;
import com.diallo.poo.App3tierP2.Entities.Book;
import com.diallo.poo.App3tierP2.Entities.Person;

public class Metier implements IMetier {  // Pourquoi eclipse propose de changer le type de la classe

	private IDao dao;

	private List<Person> people = new ArrayList<Person>();
	
	public Metier() {
	}
	
	public Metier(IDao dao) {
		this.dao = dao;
	}
	
	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	// Mon Ajout un soucis de typage sur obj borrow!!!!!!
	public int computeRemainingDays(Book b) {	
		int nbRemainingDays = 0;
		
		Book book = dao.findBook(b);
		
		//Quel est la date d'emprunt?
		 LocalDate borrowDate = book.getBorrowingDate();
		 
		//quel est la durée d'emprunt?
		 long jour = 1000 * 60 * 60 * 24;
		 Date today = new Date();
		 
		 Date borrow =  borrowDate; // probleme de type !!!!
		 
		 Long val = today.getTime() - borrow.getTime();
		 
		 int  duree = (int) (val / jour);
		 
		 
		//Calcul du nombre de jours restants;
		 
		 nbRemainingDays = b.getLoanPeriod() - duree;
		
		return nbRemainingDays;
	}


	public List<Person> getMembres() {
		return dao.findAll();
	}

	public void register(Person p) {
		dao.save(p);
	}

	public void remove(Person p) {
		dao.delete(p);
	}

	public void update(Person p) {
		dao.update(p);
	}

	public void unregister(Person p) {
		dao.delete(p);
	}
	

	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	public void register(Book b) {
		// TODO Auto-generated method stub
		
	}

	public void update(Book b) {
		// TODO Auto-generated method stub
		
	}

	public void unregister(Book b) {
		// TODO Auto-generated method stub
		
	}

	public List<Person> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}