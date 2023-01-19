package model.esecuzioni;

import model.esercizi.EsercizioInterface;
import model.visitors.VisitorEsecuzione;

public final class EsecuzioneATempo implements EsecuzioneEsercizioInterface {

	private EsercizioInterface esercizio;
	private int minuti;
	private String intensita;

	public EsecuzioneATempo(EsercizioInterface esercizio, int minuti, String intensita) {
		this.esercizio = esercizio;
		this.minuti = minuti;
		this.intensita = intensita;
	}

	@Override
	public EsercizioInterface getEsercizio() {
		return esercizio;
	}
	
	public int getMinuti() {
		return minuti;
	}
	
	public String getIntensita() {
		return intensita;
	}

	@Override
	public void accept(VisitorEsecuzione visitor) {
		visitor.visitEsecuzioneATempo(this);
	}

}
