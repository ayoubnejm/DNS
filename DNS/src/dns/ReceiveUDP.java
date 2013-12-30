package dns;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.lang.String;

/**
 * 
 * @author Ayoub Nejmeddine & Mohamed Amine Ait Belarbi
 * 
 */
public class ReceiveUDP {
	public static String receive(DatagramPacket p) {

		System.out.println("paquet 	reçu de: " + p.getAddress() + "    port "
				+ p.getPort() + "    taille " + p.getLength() + "\n");
		System.out.println("Les datas: " + Label.bytesToHexString(p.getData()));
		/* Pour décoder: on doit utiliser la phrase suivante: */

		MscDNS a = new MscDNS(p.getData());
		/*
		 * Les informations seront affichées sur l'écran automatiquement s'il
		 * n'y pas d'erreur
		 */

		/* isoler les adresses IP et retourner */
		int nbReponse = a.entete.ANcount;

		String res = "";
		for (int i = 0; i < nbReponse; i++) {

			try {
				res += "\n************************La réponse finale*********************************\nReponse "
						+ (i + 1) + "\n";
				res += "IP adresse: "
						+ InetAddress.getByAddress(a.reponses[i].RDData)
						+ "\n\n";
			} catch (UnknownHostException e) {
				res += ("Erreur de longeur pour la réponse " + (i + 1));
			}
		}
		return res;
	}
}
