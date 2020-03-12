
public class JantarDosFilosofos {
	public static void main (String[] args) {
		/* Mesa de jantar para os filósofos */
		MesaDeJantar mesa = new MesaDeJantar ();
		
		/* Criação das threads representando os cinco filósofos */
		for (int filosofo = 0; filosofo < 5; filosofo++) {
			new Filosofo("Filosofo_" + filosofo, mesa, filosofo).start();
		}
	}
}
