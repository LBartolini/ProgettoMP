package model.abbonamenti;

import model.Abbonamento;
import model.EsercizioInterface;

public final class AbbonamentoVuoto implements Abbonamento {

	@Override
	public double getPrezzo() {
		return 0;
	}

	@Override
	public boolean isEsercizioPermesso(EsercizioInterface esercizio) {
		return false;
	}

}
