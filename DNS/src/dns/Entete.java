package dns;

/**
 * 
 * @author Ayoub Nejmeddine & Mohamed Amine Ait Belarbi
 * 
 */
public class Entete {
	protected int identifiant;
	protected Parametre params;
	protected int QDcount;
	protected int ANcount;
	protected int NScount;
	protected int ARcount;

	protected int size;

	public int getSize() {
		return size;
	}

	public Entete(byte[] b, int i) {
		this.size = 12;/* La taille d'entête est fixe: 12 octets */
		this.identifiant = (b[i + 1] & 0xff) + (b[i] & 0xff) * 256;

		/*
		 * parce que b[i] est justement avant b[i+1] et (b[i]<<8)&0x0000FF00 ne
		 * marche pas dans mon ordinateur donc je fait b[i] multiplie par 256 (2
		 * puissance 8)
		 */

		this.params = new Parametre(b, i + 2);

		/*
		 * Parametre utilise deux octets donc puis on commence depuis i+3
		 */

		this.QDcount = (b[i + 5] & 0xff) + (b[i + 4] & 0xff) * 256;
		this.ANcount = (b[i + 7] & 0xff) + (b[i + 6] & 0xff) * 256;
		this.NScount = (b[i + 9] & 0xff) + (b[i + 8] & 0xff) * 256;
		this.ARcount = (b[i + 11] & 0xff) + (b[i + 10] & 0xff) * 256;

		System.out.println("***************************************");
		System.out.println("This is the entete of the DNS packet");
		System.out.println("the ID: " + this.identifiant);
		System.out.println("the QDcount: " + this.QDcount);
		System.out.println("the QNcount: " + this.ANcount);
		System.out.println("the NScount: " + this.NScount);
		System.out.println("the ARcount: " + this.ARcount);
		System.out.println("the size:" + this.size);
		System.out.println("***************************************");
	}

}
