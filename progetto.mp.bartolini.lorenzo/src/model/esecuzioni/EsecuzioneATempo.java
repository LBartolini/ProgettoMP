package model.esecuzioni;

import model.esercizi.EsercizioInterface;
import model.visitors.VisitorEsecuzione;

public final class EsecuzioneATempo implements EsecuzioneEsercizioInterface {

	private EsercizioInterface esercizio;
	private int minuti;
	private int intensità;

	public EsecuzioneATempo(EsercizioInterface esercizio, int minuti, int intensità) {
		this.esercizio = esercizio;
		this.minuti = minuti;
		this.intensità = intensità;
	}

	@Override
	public EsercizioInterface getEsercizio() {
		return esercizio;
	}
	
	public int getMinuti() {
		return minuti;
	}
	
	public int getIntensità() {
		return intensità;
	}

	@Override
	public void accept(VisitorEsecuzione visitor) {
		visitor.visitEsecuzioneATempo(this);
	}

}
