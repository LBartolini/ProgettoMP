package model.esercizi.singoli;

import model.EsecuzioneEsercizio;
import model.Palestra;
import model.esercizi.EsercizioSingolo;

public final class PancaPiana extends EsercizioSingolo {
	
	private static final int COSTO_PER_MOVE = 2;

	public PancaPiana(Palestra palestra) {
		super(palestra, "PancaPiana");
	}

	@Override
	public double getCosto(EsecuzioneEsercizio esecuzione) {
		return COSTO_PER_MOVE * esecuzione.getMOVES();
	}

}
