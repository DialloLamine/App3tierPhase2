package com.diallo.poo.App3tierGit.metier;

import java.util.ArrayList;
import java.util.List;

import com.diallo.poo.App3tierGit.dao.IDao;
import com.diallo.poo.App3tierGit.entities.Book;
import com.diallo.poo.App3tierGit.entities.Person;

public class Metier implements IMetier {

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

	public int computeRemainingDays(Book b) {	
		int nbRemainingDays = 0;
		
		//Book book = dao.findBook(b);
		
		//Quel est la date d'emprunt?
		//quel est la durée d'emprunt?
		//Calcul du nombre de jours restants
		
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
	
	//@Override
	public List<Person> findByName(String name) {
		return dao.findBy("name", name);
	}

}