package dns;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.lang.String;

/**
 * 
 * @author Ayoub Nejmeddine & Mohamed Amine Ait Belarbi
 * 
 */
public class SendUDP {
	static DatagramPacket dpr;

	public static String send(String adresse) {
		try {
			byte[] buf = new byte[1024];
			InetAddress dst = InetAddress.getByName(adresse);
			buf = Label.createQuestion("www.lifl.fr");

			DatagramPacket p = new DatagramPacket(buf, buf.length, dst, 53);
			DatagramSocket s;
			dpr = new DatagramPacket(new byte[153], 153);

			System.out.println("InetAdresse obtenu par le nom " + dst);
			/* le port défini pour DNS */
			System.out.println("\nLe paquet qu'on envoye au serveur:\n"
					+ "port: " + p.getPort() + "    Data: (0x) "
					+ Label.bytesToHexString(p.getData()));

			s = new DatagramSocket();

			s.send(p);
			System.out.println("On a envoyé le paquet DNS, sa taille:"
					+ s.getSendBufferSize());

			s.receive(dpr);

			/*
			 * recevoir le paquet retourné par le serveur et afficher les
			 * informations analysées
			 */

			/* new MscDNS(Label.GiveAPacketForSimulation()); */

		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ReceiveUDP.receive(dpr);

	}

}
