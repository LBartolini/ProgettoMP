package model.abbonamenti;

import java.util.ArrayList;
import java.util.Collection;

import model.esercizi.Esercizio;
import model.esercizi.RematoreConBilanciere;
import model.esercizi.RematoreConManubri;

public final class PacchettoSchiena extends AbbonamentoDecorator {

	public static final double PREZZO = 10;
	private Collection<Esercizio> eserciziPermessi;

	public PacchettoSchiena(Abbonamento abbonamento) {
		super(abbonamento, PREZZO);
		eserciziPermessi = new ArrayList<>();
		eserciziPermessi.add(RematoreConBilanciere.getInstance());
		eserciziPermessi.add(RematoreConManubri.getInstance());
	}

	@Override
	public Collection<Esercizio> getEserciziLocali() {
		return eserciziPermessi;
	}

}
