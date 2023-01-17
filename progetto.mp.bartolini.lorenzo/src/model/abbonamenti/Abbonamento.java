package model.abbonamenti;

import java.util.Collection;

import model.esercizi.EsercizioInterface;

public interface Abbonamento {
	
	public double getPrezzo();
	
	public Collection<EsercizioInterface> getEserciziPermessi(); 
	
	public boolean isEsercizioPermesso(EsercizioInterface esercizio);
	
}
