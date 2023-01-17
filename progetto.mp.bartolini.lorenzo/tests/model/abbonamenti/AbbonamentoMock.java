package model.abbonamenti;

import java.util.Collection;

import model.esercizi.EsercizioInterface;

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
	public Collection<EsercizioInterface> getEserciziPermessi() {
		return null;
	}

	@Override
	public boolean isEsercizioPermesso(EsercizioInterface esercizio) {
		return valore;
	}

}
