package dns;

/**
 * 
 * @author Ayoub Nejmeddine & Mohamed Amine Ait Belarbi
 * 
 */

public class MscDNS {
	protected Entete entete;
	protected Question[] questions;
	protected Reponse[] reponses;
	protected Reponse[] autorites;
	protected Reponse[] additionnels;
	private int offset;

	public MscDNS(byte[] b) {
		/* configurer les offsets et initialiser tous les champs */

		this.entete = new Entete(b, 0);

		this.questions = new Question[this.entete.QDcount];
		this.reponses = new Reponse[this.entete.ANcount];
		this.autorites = new Reponse[this.entete.ANcount];
		this.additionnels = new Reponse[this.entete.ANcount];

		this.offset = this.entete.size;
		if (this.entete.QDcount != 0) {
			for (int i = 0; i < this.entete.QDcount; i++) {
				this.questions[i] = new Question(b, offset, "This is Question "
						+ (i + 1));
				offset += this.questions[i].size;
			}
		}
		if (this.entete.ANcount != 0) {
			for (int i = 0; i < this.entete.ANcount; i++) {
				this.reponses[i] = new Reponse(b, offset, "This is Reponse "
						+ (i + 1));
				offset += this.reponses[i].size;
			}
		}
		if (this.entete.NScount != 0) {
			for (int i = 0; i < this.entete.NScount; i++) {
				this.autorites[i] = new Reponse(b, offset, "This is Autorite "
						+ (i + 1));
				offset += this.autorites[i].size;
			}
		}
		if (this.entete.ARcount != 0) {
			for (int i = 0; i < this.entete.ARcount; i++) {
				this.additionnels[i] = new Reponse(b, offset,
						"This is Additionnel " + (i + 1));
				offset += this.additionnels[i].size;
			}
		}
	}

}
