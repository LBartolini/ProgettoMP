package model;

public interface Abbonamento {
	
	public double getPrezzo();
	
	// restituisce true se il cliente puù usare il macchinario con questo abbonamento, 
	// altrimenti falso
	public boolean isMacchinarioPermesso();
	
}
