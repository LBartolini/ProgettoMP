package model.abbonamenti;

import java.util.Collection;

import model.Abbonamento;
import model.EsercizioInterface;

public final class AbbonamentoDecorator implements Abbonamento {

	private Abbonamento abbonamento;
	private Collection<EsercizioInterface> eserciziPermessi;
	private int prezzo;
	
	public AbbonamentoDecorator(Abbonamento abbonamento, Collection<EsercizioInterface> eserciziPermessi, int prezzo) {
		this.abbonamento = abbonamento;
		this.eserciziPermessi = eserciziPermessi;
		this.prezzo = prezzo;
	}

	@Override
	public double getPrezzo() {
		return prezzo + abbonamento.getPrezzo();
	}

	@Override
	public boolean isEsercizioPermesso(EsercizioInterface esercizio) {
		if(eserciziPermessi.contains(esercizio))
			return true;
		
		return abbonamento.isEsercizioPermesso(esercizio);
	}

}
