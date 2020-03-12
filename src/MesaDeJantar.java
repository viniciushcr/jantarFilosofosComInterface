
public class MesaDeJantar {
	/* filósofo pensando */
	final static int PENSANDO = 1;
	/* filósofo comendo */
	final static int COMENDO = 2;
	/* filósofo com fome */
	final static int COM_FOME = 3;
	/* Quantidade de filósofos */
	final static int QUANT_FILOSOFOS = 5;
	/* Número do primeiro filósofo */
	final static int PRIMEIRO_FILOSOFO = 0;
	/* Número do último filósofo */
	final static int ULTIMO_FILOSOFO = QUANT_FILOSOFOS - 1;
	
	/* array[0...QUANT_FILOSOFOS - 1] representando os garfos na mesa :
	 * true = garfo na mesa; false = garfo com filósofo
	 */
	boolean[] garfos = new boolean[QUANT_FILOSOFOS];
	
	/* array [0...QUANT_FILOSOFOS - 1]
	representando o estado de cada um dos filósofos
	 */
	int[] filosofos = new int[QUANT_FILOSOFOS];
	
	/* Quantas vezes cada filósofo tentou comer e não conseguiu,
	 * serve para identificar situações de Starvation
	 */
	int[] tentativasParaComer = new int[QUANT_FILOSOFOS];
	
	private MesaInterface mesaInterface;
	
	/* Construtor */
	public MesaDeJantar() {
		
		mesaInterface = new MesaInterface();
		mesaInterface.setVisible(true);
		
		/* Preenchendo os vetores de Garfos e filósofos à mesa */
		for (int i = 0; i < 5; i++) {
			/* Todos os garfos estão na mesa */
			garfos[i] = true;
			/* Todos os filósofos sentam à mesa pensando */
			filosofos[i] = PENSANDO;
			/* Nenhum filósofo tentou comer ainda */
			tentativasParaComer[i] = 0;
		}
	}
	
	/* filosofo pegando os garfos */
	synchronized void pegandoGarfos (int filosofo) {
		/* filósofo com fome */
		filosofos[filosofo] = COM_FOME;
		
		/* Deve esperar enquanto algum filósofo vizinho estive comendo */
		while (filosofos[aEsquerda(filosofo)] == COMENDO
				|| filosofos[aDireita(filosofo)] == COMENDO) {
			try {
				/* Filósofo tentou comer e não conseguiu */
				tentativasParaComer[filosofo]++;
				
				/* colocando o filosofo para esperar */
				wait();
			} catch (InterruptedException e) {
				System.out.println("Filósofo morreu de fome");
			}
		}
		
		/* Filósofo conseguiu comer */
		tentativasParaComer[filosofo] = 0;
		
		/* retirando os garfos esquerdo e direito da mesa */
		garfos[garfoEsquerdo(filosofo)] = false;
		garfos[garfoDireito(filosofo)] = false;
		/* Filósofo comendo */
		
		filosofos[filosofo] = COMENDO;
		imprimeEstadosFilosofos();
		imprimeGarfos();
		imprimeTentativasParaComer();
	}
	
	/* Filosofo devolvendo os garfos */
	synchronized void devolvendoGarfos (int filosofo) {
		/* Devolvendo os garfos esquerdo e direito da mesa */
		garfos[garfoEsquerdo(filosofo)] = true;
		garfos[garfoDireito(filosofo)] = true;
		/* Verificando se há algum filósofo vizinho com fome */
		if (filosofos[aEsquerda(filosofo)] == COM_FOME ||
				filosofos[aDireita(filosofo)] == COM_FOME) {
			/* Notifica (acorda) os vizinhos com fome */
			notifyAll();
		}
		/* Filósofo pensando */
		filosofos[filosofo] = PENSANDO;
		imprimeEstadosFilosofos();
		imprimeGarfos();
		imprimeTentativasParaComer();
	}
	/* Retorna o número do filósofo a direita */
	private int aDireita (int filosofo) {
		int direito;
		/* Caso seja o filósofo no5, a sua direita está o filósofo no1 */
		if (filosofo == ULTIMO_FILOSOFO) {
			direito = PRIMEIRO_FILOSOFO;
		} else {
			/* Caso contrário */
			direito = filosofo + 1;
		}
		return direito;
	}
	/* Retorna o número do filósofo a esquerda */
	private int aEsquerda (int filosofo) {
		int esquerdo;
		/* Caso seja o primeiro filósofo a sua esquerda está o último */
		if (filosofo == PRIMEIRO_FILOSOFO) {
			esquerdo = ULTIMO_FILOSOFO;
		} else {
			esquerdo = filosofo - 1;
		}
		return esquerdo;
	}
	/** Retorna o número do garfo a esquerda do filósofo */
	private int garfoEsquerdo (int filosofo) {
		/* O filósofo 1 possui o garfo 1 a sua esquerda e assim por diante */
		int garfoEsquerdo = filosofo;
		return garfoEsquerdo;
	}
	/** Retorna o número do garfo a direita do filósofo */
	private int garfoDireito (int filosofo) {
		int garfoDireito;
		/* O último filósofo possui o garfo 0 a sua direita*/
		if (filosofo == ULTIMO_FILOSOFO) {
			garfoDireito = 0;
		} else {
			garfoDireito = filosofo + 1;
		}
		return garfoDireito;
	}
	
	/* Imprimindo os estados dos filósofos */
	private void imprimeEstadosFilosofos () {
		String texto = "*";
		System.out.print("Filósofos = [ ");
		for (int i = 0; i < QUANT_FILOSOFOS; i++) {
			switch (filosofos[i]) {
			case PENSANDO :  
				mesaInterface.setEstadoFilosofoPensando(i);
				texto = "PENSANDO";
				break;
			case COM_FOME :
				mesaInterface.setEstadoFilosofoFome(i);
				texto = "COM_FOME";
				break;
			case COMENDO :
				mesaInterface.setEstadoFilosofoComendo(i);
				texto = "COMENDO";
				break;
			}
			System.out.print(texto + " ");
		}
		System.out.println("]");
	}
	/* Imprimindo os que estão na mesa */
	private void imprimeGarfos () {
		String garfo = "*";
		System.out.print("Garfos= [ ");
				    for (int i = 0; i < QUANT_FILOSOFOS; i++) {
				    	if (garfos[i]) {
				    		garfo = "LIVRE";
				    	} else {
				    		garfo = "OCUPADO";
				    	}
				    	System.out.print(garfo + " ");
				    }
				    System.out.println("]");
	}
	/* Imprimindo as tentativas de comer dos dos filósofos */
	private void imprimeTentativasParaComer () {
		System.out.print("Tentou comer = [ ");
		for (int i = 0; i < QUANT_FILOSOFOS; i++) {
			System.out.print(filosofos[i] + " ");
		}
		System.out.println("]");
	}
}
