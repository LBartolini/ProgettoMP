package model;

public interface Esercizio {
	
	public String getNomeEsercizio();
	
	public double getCosto(EsecuzioneEsercizio esecuzione);
	
	public void acceptVisitor(VisitorEsercizio visitor);
	
}
