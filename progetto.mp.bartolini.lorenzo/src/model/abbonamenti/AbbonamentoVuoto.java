package model.abbonamenti;

import java.util.ArrayList;
import java.util.Collection;

import model.esercizi.EsercizioInterface;

public final class AbbonamentoVuoto implements Abbonamento {

	@Override
	public double getPrezzo() {
		return 0;
	}

	@Override
	public Collection<EsercizioInterface> getEserciziPermessi() {
		return new ArrayList<>();
	}
	
	@Override
	public boolean isEsercizioPermesso(EsercizioInterface esercizio) {
		return false;
	}	

}
