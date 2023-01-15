package model.abbonamenti;

import model.Abbonamento;
import model.Esercizio;

public final class AbbonamentoVuoto implements Abbonamento {

	@Override
	public double getPrezzo() {
		return 0;
	}

	@Override
	public boolean isEsercizioPermesso(Esercizio esercizio) {
		return false;
	}

}
