package model.esecuzioni;

import model.esercizi.Esercizio;
import model.visitors.VisitorEsecuzione;

public final class EsecuzioneATempo implements Esecuzione {

	private Esercizio esercizio;
	private int minuti;
	private String intensita;

	public EsecuzioneATempo(Esercizio esercizio, int minuti, String intensita) {
		this.esercizio = esercizio;
		this.minuti = minuti;
		this.intensita = intensita;
	}

	@Override
	public Esercizio getEsercizio() {
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
