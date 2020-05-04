package com.diallo.poo.App3tierGitM;



import com.diallo.poo.App3tierGit.ui.IUi;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner
{
	private static IUi ui;

	public static void main( String[] args )
    {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		
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
