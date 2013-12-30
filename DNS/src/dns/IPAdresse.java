package dns;

/**
 * 
 * @author Ayoub Nejmeddine & Mohamed Amine Ait Belarbi
 * 
 */


public class IPAdresse {

	/* c'est justement la principale méthode à exécuter */
	/* Elle va afficher tous les détails du paquet retourné */
	/* puis elle va isoler les adresses IP dans les réponses */
	/* et les afficher ici */
	public static void main(String args[]) {
		System.out
				.println("Vous voulez consulter l'adresse IP de : " + args[0]);
		String res = SendUDP.send(args[0]);
		System.out.println("\n\nLes adresses IP:");
		System.out.println(res);
	}
}
