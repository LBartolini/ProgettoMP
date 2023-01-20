package model.abbonamenti;

import java.util.ArrayList;
import java.util.Collection;

import model.esercizi.Esercizio;

public final class AbbonamentoVuoto implements Abbonamento {

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
		return false;
	}	

}
