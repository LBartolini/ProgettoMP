package model.abbonamenti;

import java.util.ArrayList;
import java.util.Collection;

import model.esercizi.Esercizio;

public class AbbonamentoMock implements Abbonamento {

	private boolean valore;

	public AbbonamentoMock(boolean valore) {
		this.valore = valore;
	}

	@Override
	public double getPrezzo() {
		return 0;
	}

	@Override
	public Collection<Esercizio> getEserciziPermessi() {
		return new ArrayList<>();
	}

	@Override
	public boolean isEsercizioPermesso(Esercizio esercizio) {
		return valore;
	}

}
