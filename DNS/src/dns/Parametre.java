package dns;

/**
 * 
 * @author Ayoub Nejmeddine & Mohamed Amine Ait Belarbi
 * 
 */
public class Parametre {
	boolean QR;/* réponse pour true et question pour false */
	int OPCODE;
	boolean AA;
	boolean TC;
	boolean RD;
	boolean RA;
	int RCODE;

	public Parametre(byte[] b, int i) {
		this.QR = ((b[i] & 0x80) == 0x80);
		/* prendre le premier bit */
		this.OPCODE = (b[i] & 0x78);
		/* prendre du 2 ème bit à 5 ème bit */
		this.AA = ((b[i] & 0x04) == 0x04);
		/* prendre le 6 ème bit */
		this.TC = ((b[i] & 0x02) == 0x02);
		this.RD = ((b[i] & 0x01) == 0x01);
		this.RA = ((b[i + 1] & 0x80) == 0x80);
		/* prendre le premier bit de 2 ème octet */
		this.RCODE = (b[i + 1] & 0x15);

		if (RCODE == 5) {
			System.out
					.println("\nErreur: Le code retourné est 5. C.a.d le serveur a refusé le requête.\n");
			System.exit(0);
		}

		System.out.println("#######################################");
		System.out.println("This is the params of the entete");
		System.out.println("the QR: " + this.QR);
		System.out.println("the OPCODE: " + this.OPCODE);
		System.out.println("the AA: " + this.AA);
		System.out.println("the TC: " + this.TC);
		System.out.println("the RD: " + this.RD);
		System.out.println("the RA " + this.RA);
		System.out.println("the RCODE " + this.RCODE);
		/*
		 *on utilise une méthode spéciale ici pour transformer le tableau de
		 * byte à String
		 */
		System.out.println("#######################################");
	}
}
