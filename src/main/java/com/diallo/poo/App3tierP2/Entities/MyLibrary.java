package com.diallo.poo.App3tierP2.Entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Repr�sente la biblioth�que
 * D�finie par son nom, la liste des membres et la liste des livres.
 *  
 * @author Diallo Lamine
 * @version 0.1
 * @see Book
 * @see Person
 */
public class MyLibrary {
	/**
	 * Nom de la biblioth�que
	 */
	private String name;
	
	/**
	 * Liste des livres
	 */
	private ArrayList<Book> books;
	
	/**
	 * Liste des membres
	 */
	private ArrayList<Person> people;
	
	/**
	 * Cr�e une biblioth�que en sp�cifiant son nom
	 * 
	 * @param name Nom de la biblioth�que
	 */
	public MyLibrary(String name) {
		this.name = name;
		this.books = new ArrayList<Book>();
		this.people = new ArrayList<Person>();
	}

	/**
	 * Renvoie le nom de la biblioth�que
	 * 
	 * @return Le nom de la biblioth�que
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom de la biblioth�que
	 * 
	 * @param name Nouveau nom de la biblioth�que
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Renvoie la liste des livres
	 * 
	 * @return la liste des livres
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}

	/**
	 * Renvoie la liste des membres
	 * 
	 * @return la liste des membres
	 */
	public ArrayList<Person> getPeople() {
		return people;
	}

	@Override
	public String toString() {
		final int maxLen = 3;
		return "\n MyLibrary [name = " + name 
				+ "\n\t books = "
				+ (books != null ? books.subList(0, Math.min(books.size(), maxLen)) : null) 
				+ "\n\t people="
				+ (people != null ? people.subList(0, Math.min(people.size(), maxLen)) : null) + "]";
	}

	public void addBook(Book book) {
		this.books.add(book);
	}

	public void addPerson(Person person) {
		this.people.add(person);
	}
	
	public int printBooks() {
		int cpt = 0;
		Iterator<Book> it = this.getBooks().iterator();
		
		while(it.hasNext()) {
			Book b = it.next();
								
			System.out.println(++cpt + ": " 
					+b.getTitle() + " - "
					+b.getAuthor());
		}
		return cpt;
	}

	public int printMembers() {
		int cpt;
		cpt = 0;
		Iterator<Person> itp = this.getPeople().iterator();
		
		while(itp.hasNext()) {
			Person p = itp.next();
			
			System.out.println(++cpt + ": " + p.getName());
		}
		return cpt;
	}
	
	public int loadMembers(String filename) {
		int cpt = 0;
		
		File f = new File(filename);
		
		if(f.exists()) {
			FileReader fr = null;
			BufferedReader br = null;
			String[] data = null;
			
			try {
				try {
					fr = new FileReader(f);
					br = new BufferedReader(fr);

					//Convertir en objet Person
					//StringTokenizer st = new StringTokenizer(ligne, ";");
					String ligne = br.readLine();
					
					//Lire une ligne du fichier					
					while ((ligne = br.readLine()) != null) {
						//"a7aa0ae7-9ce3-44bc-a72a-894edb9a4653;Bob Smith;2;01-03-20"
						data = ligne.split(";");
						Person p = new Person(UUID.fromString(data[0]), data[1]);
						
						byte nbMaxBooks = Byte.parseByte(data[2]);
						
						LocalDate registrationDate = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd-MM-yy"));
						
						p.setMaxBooks(nbMaxBooks);
						p.setRegistrationDate(registrationDate);
						
						//ajouter cet Person dans people
						this.people.add(p);
						cpt++;
					}
				} finally {
					br.close();
				}
			} catch (IOException e) {
				
			}
			
			System.out.println(people);
		} 
		
		return cpt;
	}
	
	public int loadMembersFromCSVFile(String filename) {
		int cpt = 0;
		
		File f = new File(filename);
		
		if(f.exists()) {
			FileReader fr = null;
			
			try {
				try {
					fr = new FileReader(f);
					Person p;
					String id;
					String name;
					String nbMaxBooks;
					String registrationDate;
					
					Iterable<CSVRecord> records = CSVFormat.EXCEL
							.withSkipHeaderRecord(true)
							.withHeader("id","name","maxBooks","registrationDate")
							.withDelimiter(';').parse(fr);
					
					for(CSVRecord record : records) {
						id = record.get("id");
						name = record.get("name");
						nbMaxBooks = record.get("maxBooks");
						registrationDate = record.get("registrationDate");
						
						p = new Person(UUID.fromString(id), name);
						p.setMaxBooks(Byte.parseByte(nbMaxBooks));
						p.setRegistrationDate(LocalDate.parse(registrationDate, DateTimeFormatter.ofPattern("dd-MM-yy")));
						
						//ajouter cet Person dans people
						this.people.add(p);
						cpt++;
					}
				} finally {
					fr.close();
				}
			} catch (IOException e) {
				
			}
			
			System.out.println(people);
		} 
		
		return cpt;
	}
	

	public boolean saveMembersToCSVFile(String filename) {
		File f = new File(filename);
		FileWriter fw = null;
		
		try {
			try {
				if(!f.exists()) {
					f.createNewFile();
				}
				
				fw = new FileWriter(f);
				Charset charset = Charset.forName("Cp1252");
				
				final CSVPrinter printer = CSVFormat.EXCEL
					.withDelimiter(';').print(f, charset);
				
				printer.printRecord("id","name","maxBooks","registrationDate");
				
				for(Person p : people) {
					printer.print(p.getId());
					printer.print(p.getName());
					printer.print(p.getMaxBooks());
					
					String registrationDate = p.getRegistrationDate()
							.format(DateTimeFormatter.ofPattern("dd-MM-yy")).toString();
					
					printer.print(registrationDate);
					printer.println();
				}
				
				printer.close();
				
//				CSVPrinter printer2 = new CSVPrinter(fw, CSVFormat.EXCEL);
//				printer2.printRecord("id","name","maxBooks","registrationDate");
//				printer2.close();
			} finally {
				fw.close();
			}
		} catch(IOException e) {
			
		}
		
		return false;
	}

	public int loadMembersFromXMLFile(String filename) {
		int cpt = 0;
		
		File f = new File(filename);
		
		if(f.exists()) {
			FileReader fr = null;
			
			try {
				try {
					fr = new FileReader(f);
					Person p;
					
					XStream xs = new XStream(new DomDriver());
					
					//Configuration du parser XML
					xs.alias("person", Person.class);
					
					//Lecture
					this.people = (ArrayList<Person>) xs.fromXML(fr);					
				} finally {
					fr.close();
				}
			} catch (IOException e) {
				
			}
			
			System.out.println(people);
		} 
		
		return cpt;
	}
	
	public boolean saveMembersToXMLFile(String filename) {
		File f = new File(filename);
		FileWriter fw = null;
		
		try {
			try {
				if(!f.exists()) {
					f.createNewFile();
				}
				
				fw = new FileWriter(f);
				
				XStream xs = new XStream(new DomDriver());
				
				//Configuration du parser XML
				xs.alias("person", Person.class);
				
				//Conversion en XML
				String xml = xs.toXML(people);
				
				//Sauvegarder dans un fichier texte
				fw.write(xml);
			} finally {
				fw.close();
			}
		} catch(IOException e) {
			
		}
		
		return false;
	}
	
	public Person isMemberExist(String name){

		String nameInTheList= null;
		Person personToFind;
		Iterator<Person> it = this.getPeople().iterator();

		while(it.hasNext()) {
		   personToFind = it.next();
		   nameInTheList= personToFind.getName();

		    if(nameInTheList.equals(name)) {
		        return personToFind;
		    }
		}
		 return null;
		}
	
}
