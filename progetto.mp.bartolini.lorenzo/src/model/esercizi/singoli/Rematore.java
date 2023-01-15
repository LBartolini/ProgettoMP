package model.esercizi.singoli;

import model.EsecuzioneEsercizio;
import model.Palestra;
import model.esercizi.EsercizioSingolo;

public final class Rematore extends EsercizioSingolo {
	
	private static final int COSTO_PER_MOVE = 1;

	public Rematore(Palestra palestra) {
		super(palestra, "Rematore");
	}

	@Override
	public double getCosto(EsecuzioneEsercizio esecuzione) {
		return COSTO_PER_MOVE * esecuzione.getMOVES();
	}

}
