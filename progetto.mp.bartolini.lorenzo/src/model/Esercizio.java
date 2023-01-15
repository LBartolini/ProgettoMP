package model;

public interface Esercizio {

	public void prenota(Cliente cliente);
	
	public void rimuoviPrenotazione(Cliente cliente);
	
	public boolean occupa(Cliente cliente);
	
	// funge anche da notify
	public boolean rilascia(Cliente cliente);
	
	public int getPostiDisponibili();
	
	public double getCosto(EsecuzioneEsercizio esecuzione);
	
	public void acceptVisitor(VisitorEsercizio visitor);
	
}
