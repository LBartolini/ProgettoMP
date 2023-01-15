package model.esercizi.multipli;

import model.EsecuzioneEsercizio;
import model.Palestra;
import model.esercizi.EsercizioMultiplo;

public final class Pugilato extends EsercizioMultiplo {
	
	private static final int COSTO_PER_MOVE = 5;

	public Pugilato(Palestra palestra, String nomeEsercizio, int maxClientiInContemporanea) {
		super(palestra, "Pugilato", maxClientiInContemporanea);
	}

	@Override
	public double getCosto(EsecuzioneEsercizio esecuzione) {
		return COSTO_PER_MOVE * esecuzione.getMOVES();
	}

}
