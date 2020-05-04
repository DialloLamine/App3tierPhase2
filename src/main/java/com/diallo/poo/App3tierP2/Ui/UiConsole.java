package com.diallo.poo.App3tierP2.Ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.diallo.poo.App3tierP2.Entities.Person;
import com.diallo.poo.App3tierP2.Metier.IMetier;

public class UiConsole implements IUi {

	private IMetier metier;
	
	private Scanner s = new Scanner(System.in);

	private List<Person> membres = new ArrayList<Person>();
	
	private String message;

	public UiConsole() {
	}
	
	public UiConsole(IMetier metier) {
		this.metier = metier;
	}

	public IMetier getMetier() {
		return metier;
	}

	public void setMetier(IMetier metier) {
		this.metier = metier;
	}

	public void run() {
		int choix;
		
		//Affichage menu
		System.out.println("\t1- Afficher tous les membres");
		System.out.println("\t2- Ajouter un membre");
		System.out.println("\t3- Retirer un membre");
		System.out.println("\t0- Quitter");
		
		// a implementer dans la semaine du 04 Mai 20
		System.out.println("\t11- Afficher tous les Livres");
		System.out.println("\t12- Ajouter un Livre");
		System.out.println("\t13- Retirer un Livre");
		System.out.println("\t0- Quitter");

		//Lecture du choix de l'utilisateur
		choix = s.nextInt(); s.nextLine();
		
		//Traitement de la commande de l'utilisateur
		switch(choix) {
			case 0:
				return;
			case 1:
				//Récupérer la liste des membres
				membres = metier.getMembres();
				
				//Afficher les membres dans la console
				printMembers();
				
				message = "";
				break;
			case 2:
				String name = null;
				
				//Demande d'encodage des données
				System.out.print("Veuillez entre le nom du membre: ");
				
				name = s.nextLine();
				
				//Instanciation du membre
				Person newMembre = new Person(UUID.randomUUID(), name);
				
				//Ajout dans le support de stockage
				metier.register(newMembre);
				
				message = "Inscription terminée.";
				break;
			case 3:
				//....
				Person p = null;
				
				metier.unregister(p);
				
				message = "Membre supprimé";
			default:
				System.out.println("Commande inconnue!");
		}
		
		//Affichage d'un message de retour
		System.out.println(message);
	}

	private void printMembers() {
		Iterator<Person> it = membres.iterator();
		
		while(it.hasNext()) {
			Person p = it.next();
			
			System.out.println("Nom: "+p.getName() + "\t Inscrit le: " + p.getRegistrationDate());
		}
	}

}