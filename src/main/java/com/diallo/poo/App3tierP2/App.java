package com.diallo.poo.App3tierP2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.diallo.poo.App3tierP2.Ui.IUi;



/**
 * Hello world!
 *
 */
@SpringBootConfiguration
public class App implements CommandLineRunner
{
	private static IUi ui;
	
	/*
	import org.springframework.boot.CommandLineRunner;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.support.ClassPathXmlApplicationContext;
*/

	public static void main( String[] args )
    {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springP2-config.xml");// a chercher plustard
		
		
		
		ui = (IUi) ctx.getBean("uiConsole");
		
        //Avant lancement du programme
		System.out.println( "Bonjour et bienvenue!" );
        
        //Lancement du programme
        ui.run();
        
        //Après la fin du programme
        System.out.println("Merci d'avoir utilisé ce programme.");
    }

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
}