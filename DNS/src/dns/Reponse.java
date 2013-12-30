package dns;

/**
 * 
 * @author Ayoub Nejmeddine & Mohamed Amine Ait Belarbi
 * 
 */
public class Reponse {
	protected String nom;
	protected int type;
	protected int classe;
	protected int TTL;
	protected int RDLength;
	protected byte[] RDData;
	protected int size;

	public Reponse(byte[] b, int i, String utilisation) {

		/*
		 * car les champs Réponse, Autorité, Additionnel sont représentés de la
		 * même manière
		 */
		/*
		 * on utilise seulement la classe Reponse pour décoder les trois champs
		 * différents
		 */

		this.nom = "" + (char) (b[i] & 0xff) + (char) (b[i + 1] & 0xff);
		this.type = (b[i + 2] & 0xff) * 256 + (b[i + 3] & 0xff);
		this.classe = (b[i + 4] & 0xff) * 256 + (b[i + 5] & 0xff);
		this.TTL = (b[i + 6] & 0xff) * 16777216 + (b[i + 7] & 0xff) * 65536
				+ (b[i + 8] & 0xff) * 256 + (b[i + 9] & 0xff);
		this.RDLength = (b[i + 10] & 0xff) * 256 + (b[i + 11] & 0xff);

		this.RDData = new byte[this.RDLength];

		for (int j = 0; j < this.RDLength; j++) {
			this.RDData[j] = b[i + 12 + j];
		}

		this.size = 12 + this.RDLength;

		/* affichage des informations */
		System.out.println("***************************************");
		System.out.println(utilisation);
		System.out.println("the name: " + this.nom);
		System.out.println("the type: " + this.type);
		System.out.println("the class: " + this.classe);
		System.out.println("the TTL: " + this.TTL);
		System.out.println("the RDLength: " + this.RDLength);
		System.out.println("the RDData: 0x "
				+ Label.bytesToHexString(this.RDData));
		/*
		 * On utilise une méthode spéciale ici pour transformer le tableau de
		 * byte à String
		 */
		System.out.println("***************************************");

		/* affichage termine */

	}
	/*
	 * public static void main(String args[]){ byte[] test=new byte[28];
	 * test[0]=
	 * 0x03;test[1]=0x77;test[2]=0x77;test[3]=0x77;test[4]=0x04;test[5]=0x6c
	 * ;test[6]=0x69; test[7]=(byte)0x66;test[8]=0x6c;test[9]=0x00;
	 * test[10]=0x00;test[11]=1;test[12]=0;test[13]=1;
	 * test[14]=0x67;test[15]=0x67;test[16]=0;test[17]=0x01;test[18]=0;
	 * test[19]=2;test[20]=0;test[21]=0;test[22]=0x10;test[23]=0;test[24]=0;
	 * test[25]=0x02;test[26]=(byte)0xFF;test[27]=(byte)0xff;
	 * 
	 * new Reponse(test,14,"This is reponse 1"); }
	 */
}
