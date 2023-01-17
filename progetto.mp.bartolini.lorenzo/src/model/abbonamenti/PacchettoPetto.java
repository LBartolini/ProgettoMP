package model.abbonamenti;

import java.util.ArrayList;
import java.util.Collection;

import model.esercizi.EsercizioInterface;
import model.esercizi.PancaPiana;

public final class PacchettoPetto extends AbbonamentoDecorator {
	
	public static final double PREZZO = 20;
	private Collection<EsercizioInterface> eserciziPermessi;

	public PacchettoPetto(Abbonamento abbonamento) {
		super(abbonamento, PREZZO);
		eserciziPermessi = new ArrayList<>();
		eserciziPermessi.add(PancaPiana.getInstance());
	}

	@Override
	public Collection<EsercizioInterface> getEserciziLocali() {
		return eserciziPermessi;
	}

}
