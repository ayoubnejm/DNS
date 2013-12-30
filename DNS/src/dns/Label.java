package dns;

/**
 * 
 * @author Ayoub Nejmeddine & Mohamed Amine Ait Belarbi
 * 
 */
public class Label {
	/* une classe qui donne des fonctions très utiles pour décoder un paquet DNS */

	static int id = 0xbb;

	public static String bytesToHexString(byte[] src) {
		/* Il transforme un tableau de byte à String(0x) */

		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static byte[] createLabel(String adresse) {
		/* créer un label depuis une adresse symbolique */

		byte resultat[] = new byte[adresse.length() + 1];
		int i = 0;
		String contenus[] = adresse.split("\\.");

		for (String a : contenus)
		/*
		 * parcourir tous les sous-chaînes de caractères comprises dans le
		 * tableau
		 */
		{
			resultat[i++] = (byte) a.length();
			for (int j = 0; j < a.length(); j++)
				resultat[i++] = (byte) a.charAt(j);
		}
		return resultat;
	}

	public static byte[] createQuestion(String adresse) {
		/* créer une requête complète pour envoyer au serveur */

		byte question[] = new byte[adresse.length() + 18];
		byte label[] = createLabel(adresse);
		int i = 0;

		question[i++] = 8;
		question[i++] = (byte) id++;
		question[i++] = 1;
		question[i++] = 0;
		question[i++] = 0;
		question[i++] = 1;
		question[i++] = 0;
		question[i++] = 0;
		question[i++] = 0;
		question[i++] = 0;
		question[i++] = 0;
		question[i++] = 0;

		for (int j = 0; j < adresse.length() + 1; j++) {
			question[i++] = label[j];
		}
		question[i++] = 0;
		question[i++] = 0;
		question[i++] = 1;
		question[i++] = 0;
		question[i] = 1;

		/*
		 * Le type est par défault: c.a.d host adress La classe est internet
		 */
		return question;

	}

	public static byte[] GiveAPacketForSimulation() {
		byte[] s = new byte[113];
		/* entete */
		s[0] = (byte) 0x08;
		s[1] = (byte) 0xbb;
		s[2] = 1;
		s[3] = 0;
		s[4] = 0;
		s[5] = 1;
		s[6] = 0;
		s[7] = 2;
		s[8] = 0;
		s[9] = 2;
		s[10] = 0;
		s[11] = 2;

		/* question */
		s[12] = 0x03;
		s[13] = 0x77;
		s[14] = 0x77;
		s[15] = 0x77;
		s[16] = 0x04;
		s[17] = 0x6c;
		s[18] = 0x69;
		s[19] = 0x66;
		s[20] = 0x6c;
		s[21] = 0x02;
		s[22] = 0X66;
		s[23] = 0x72;
		s[24] = 0;
		s[25] = 0;
		s[26] = 1;
		s[27] = 0;
		s[28] = 1;

		/* reponses et autorités et additionnels */

		for (int i = 0; i < 6; i++) {
			s[29 + 14 * i] = 0x67;
			s[30 + 14 * i] = 0x67;
			s[31 + 14 * i] = 0;
			s[32 + 14 * i] = 0x01;
			s[33 + 14 * i] = 0;
			s[34 + 14 * i] = 2;
			s[35 + 14 * i] = 0;
			s[36 + 14 * i] = 0;
			s[37 + 14 * i] = 0x10;
			s[38 + 14 * i] = 0;
			s[39 + 14 * i] = 0;
			s[40 + 14 * i] = 0x02;
			s[41 + 14 * i] = (byte) 0xFF;
			s[42 + 14 * i] = (byte) 0xff;
		}
		return s;
	}

	public static int byteTableauToIP(byte[] b) {
		int c = 0;
		int length = b.length, buf;
		for (int i = 0; i < length; i++) {
			buf = b[i] & 0xff;
			for (int j = 0; j < i; j++) {
				buf *= 256;
			}
			c += buf;
		}
		return c;
	}

	public static String test(byte[] b) {
		String a = new String(b);
		return a;
	}

	public static void change(int a, int b) {
		int c;
		c = a;
		a = b;
		b = c;
	}

	/*
	 * public static void main(String args[]){
	 * 
	 * /* System.out.println(bytesToHexString(createQuestion("www.lifl.fr")));
	 * /* byte[] test=new byte[2]; test[0]=(byte)0x01; test[1]=(byte)0x01; int
	 * c=(int)(test[1]&0xff)+(int)(test[0]&0xff)*256;
	 * 
	 * System.out.println(c);
	 */

	/* } */
}
