package model;

public interface EsercizioInterface {
	
	public String getNomeEsercizio();
	
	public double getCosto(EsecuzioneEsercizioInterface esecuzione);
	
	public void acceptVisitor(VisitorEsercizioInterface visitor);
	
}
