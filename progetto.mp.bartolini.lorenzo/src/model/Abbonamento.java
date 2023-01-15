package model;

public interface Abbonamento {
	
	public abstract double getPrezzo();
	
	public boolean isEsercizioPermesso(EsercizioInterface esercizio);
	
}
