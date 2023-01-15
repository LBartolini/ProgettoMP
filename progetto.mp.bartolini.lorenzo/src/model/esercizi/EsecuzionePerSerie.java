package model.esercizi;

import model.EsecuzioneEsercizio;

public class EsecuzionePerSerie implements EsecuzioneEsercizio {

	private int numSerie;
	private int numRipetizioni;

	public EsecuzionePerSerie(int numSerie, int numRipetizioni) {
		this.numSerie = numSerie;
		this.numRipetizioni = numRipetizioni;
	}

	@Override
	public int getMOVES() {
		return numRipetizioni * numSerie;
	}

}
