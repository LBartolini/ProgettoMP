package model.abbonamenti;

import java.util.Collection;

import model.esercizi.Esercizio;

public interface Abbonamento {
	
	public double getPrezzo();
	
	public Collection<Esercizio> getEserciziPermessi(); 
	
	public boolean isEsercizioPermesso(Esercizio esercizio);
	
}
