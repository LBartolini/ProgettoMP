package model.abbonamenti;

import java.util.Collection;

import model.Abbonamento;
import model.Esercizio;

public final class AbbonamentoDecorator implements Abbonamento {

	private Abbonamento abbonamento;
	private Collection<Esercizio> eserciziPermessi;
	private int prezzo;
	
	public AbbonamentoDecorator(Abbonamento abbonamento, Collection<Esercizio> eserciziPermessi, int prezzo) {
		this.abbonamento = abbonamento;
		this.eserciziPermessi = eserciziPermessi;
		this.prezzo = prezzo;
	}

	@Override
	public double getPrezzo() {
		return prezzo + abbonamento.getPrezzo();
	}

	@Override
	public boolean isEsercizioPermesso(Esercizio esercizio) {
		if(eserciziPermessi.contains(esercizio))
			return true;
		
		return abbonamento.isEsercizioPermesso(esercizio);
	}

}
