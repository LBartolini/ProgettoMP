package model.esercizi;

import model.EsecuzioneEsercizio;

public final class EsecuzioneATempo implements EsecuzioneEsercizio {

	private int minuti;

	public EsecuzioneATempo(int minuti) {
		this.minuti = minuti;
	}

	@Override
	public int getMOVES() {
		return 3*minuti;
	}

}
