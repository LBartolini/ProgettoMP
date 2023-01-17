package model.abbonamenti;

import java.util.ArrayList;
import java.util.Collection;

import model.esercizi.EsercizioInterface;
import model.esercizi.RematoreConBilanciere;
import model.esercizi.RematoreConManubri;

public final class PacchettoSchiena extends AbbonamentoDecorator {

	public static final double PREZZO = 10;
	private Collection<EsercizioInterface> eserciziPermessi;

	public PacchettoSchiena(Abbonamento abbonamento) {
		super(abbonamento, PREZZO);
		eserciziPermessi = new ArrayList<>();
		eserciziPermessi.add(RematoreConBilanciere.getInstance());
		eserciziPermessi.add(RematoreConManubri.getInstance());
	}

	@Override
	public Collection<EsercizioInterface> getEserciziLocali() {
		return eserciziPermessi;
	}

}
