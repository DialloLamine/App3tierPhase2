package com.diallo.poo.App3tierGit.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.diallo.poo.App3tierGit.entities.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DaoFile implements IDao {

	private List<Person> people = new ArrayList<Person>();
	private String filename;
	
	public DaoFile() {
		this.filename = "data\\membres.xml";
	}

	public DaoFile(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<Person> findAll() {
		this.people = readXMLFile(filename);
		
		return people;
	}

	public Person findById(UUID id) {
		findAll();
		
		Person p = null;
		Iterator<Person> it = people.iterator();
		
		while(it.hasNext()) {
			p = it.next();
			
			if(p.getId().equals(id)) {
				return p;
			}
		}
		
		return null;
	}

	public List<Person> findBy(String property, String value) {
findAll();
		
		ArrayList<Person> result = new ArrayList<Person>();
		
		String getterName = "get"+property.substring(0,1).toUpperCase()+property.substring(1);
		Method method =	null;
		
		try {
			method = Person.class.getDeclaredMethod(getterName, null);
			
			Iterator<Person> it = this.people.iterator();
			
			while(it.hasNext()) {
				Person p = it.next();
				String ps = method.invoke(p).toString();
				
				if(ps.equals(value)) {
					result.add(p);
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public void delete(UUID id) {
		findAll();
		
		Person p = null;
		Iterator<Person> it = people.iterator();
		
		while(it.hasNext()) {
			p = it.next();
			
			if(p.getId().equals(id)) {
				it.remove();
			}
		}
		
		//Ecriture des données
		writeXMLFile(filename, this.people);
	}

	public void delete(Person p) {
		Iterator<Person> it = people.iterator();
		
		while(it.hasNext()) {
			Person currentPerson = it.next();
			
			if(currentPerson.equals(p)) {
				it.remove();
			}
		}
		
		//Ecriture des données
		writeXMLFile(filename, this.people);
	}

	public void update(Person p) {
		findAll();
		
		boolean trouve = false;
		Person currentPerson = null;
		Iterator<Person> it = people.iterator();
		
		while(it.hasNext()) {
			currentPerson = it.next();
			
			if(currentPerson.getId().equals(p.getId())) {
				it.remove();
				trouve = true;
			}
		}
		
		if(trouve) {
			people.add(p);
		}
		
		//Ecriture des données
		writeXMLFile(filename, this.people);
	}

	public void save(Person p) {
		findAll();
		
		boolean trouve = false;
		Person currentPerson = null;
		Iterator<Person> it = people.iterator();
		
		while(it.hasNext()) {
			currentPerson = it.next();
			
			if(currentPerson.getId().equals(p.getId())) {
				update(p);
				return;
			}
		}
		
		if(!trouve) {
			people.add(p);
		}
		
		//Ecriture des données
		writeXMLFile(filename, this.people);
	}
	
	private List<Person> readXMLFile(String filename) {
		List<Person> list = new ArrayList<Person>();
		
		String xml = readFile(filename);
		
		list = convertFromXMLStringtoList(xml);
		
		return list;
	}

	private List<Person> convertFromXMLStringtoList(String xml) {
		XStream xs = new XStream(new DomDriver()); // What is thisssssssss
		
		//Configuration du parser XML
		xs.alias("person", Person.class);
		
		return (List<Person>) xs.fromXML(xml);
	}

	private String readFile(String filename) {
		String content = null;
		String line = null;
		StringBuilder sb = new StringBuilder();
		File f = new File(filename);
		FileReader fr = null;
		BufferedReader br = null;
		
		if(f.exists()) {
			try {
				try {
					fr = new FileReader(f);
					br = new BufferedReader(fr);
					
					while((line = br.readLine()) != null) {
						sb.append(line);
					}
				} finally {
					br.close();
					fr.close();
					System.out.println("Fin de lecture");
				}
			} catch(Exception e) {
				
			}
		}
		
		return sb.toString();
	}

	private void writeXMLFile(String filename, List<Person> people) {
		File f = new File(filename);
		FileWriter fr = null;
		BufferedWriter br = null;
		
		XStream xs = new XStream(new DomDriver());
		
		//Configuration du parser XML
		xs.alias("person", Person.class);
		
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		try {
			try {
				fr = new FileWriter(f);
				br = new BufferedWriter(fr);
				
				xs.toXML(people, br);
			} finally {
				br.close();
				fr.close();
				System.out.println("Fin d'écriture.");
			}
		} catch(Exception e) {
			
		}
	}

}
