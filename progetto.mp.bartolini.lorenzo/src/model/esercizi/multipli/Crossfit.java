package model.esercizi.multipli;

import model.EsecuzioneEsercizio;
import model.Palestra;
import model.esercizi.EsercizioMultiplo;

public final class Crossfit extends EsercizioMultiplo {
	
	private static final int COSTO_PER_MOVE = 3;

	public Crossfit(Palestra palestra, String nomeEsercizio, int maxClientiInContemporanea) {
		super(palestra, "Crossfit", maxClientiInContemporanea);
	}

	@Override
	public double getCosto(EsecuzioneEsercizio esecuzione) {
		return COSTO_PER_MOVE * esecuzione.getMOVES();
	}

}
