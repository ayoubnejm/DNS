package dns;

/**
 * 
 * @author Ayoub Nejmeddine & Mohamed Amine Ait Belarbi
 * 
 */
public class Question {
	protected static String adresse;
	protected int type;
	protected int classe;
	protected int size;

	public Question(byte[] b, int i, String utilisation) {
		int place = i;
		int lengthBeforePoint;
		this.adresse = "";
		/* donner adresse une valeur initiale */

		while (b[place] != 0) {
			lengthBeforePoint = (int) (b[place] & 0xff);
			for (int j = 0; j < lengthBeforePoint; j++) {
				this.adresse += (char) (b[place + j + 1] & 0xff);
			}
			place = place + lengthBeforePoint + 1;
			if (b[place] != 0)
				this.adresse += ".";
		}
		/*
		 * traiter les octets de label pour obtenir l'adresse comprise dans ce
		 * label
		 */

		this.type = (b[place + 1] & 0xff) * 256 + (b[place + 2] & 0xff);
		this.classe = (b[place + 3] & 0xff) * 256 + (b[place + 4] & 0xff);
		this.size = place - i + 5;

		System.out.println("***************************************");
		System.out.println(utilisation);
		System.out.println("the address: " + this.adresse);
		System.out.println("the type: " + this.type);
		System.out.println("the class: " + this.classe);
		System.out.println("the size:" + this.size);
		System.out.println("***************************************");

		

	}

	/*
	 * test pour cette classe
	 */
	public static void main(String args[]) {
		byte[] test = new byte[14];
		test[0] = 0x03;
		test[1] = 0x77;
		test[2] = 0x77;
		test[3] = 0x77;
		test[4] = 0x04;
		test[5] = 0x6c;
		test[6] = 0x69;
		test[7] = 0x66;
		test[8] = 0x6c;
		test[9] = 0x00;
		test[10] = 0x00;
		test[11] = 1;
		test[12] = 0;
		test[13] = 1;
		System.out.println(new Question(test, 0, adresse).size);

		new MscDNS(Label.GiveAPacketForSimulation());
	}

}
