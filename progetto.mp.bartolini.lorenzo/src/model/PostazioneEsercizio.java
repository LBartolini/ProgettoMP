package model;

public interface PostazioneEsercizio {
	
	public void prenota(Cliente cliente);
	
	public void rimuoviPrenotazione(Cliente cliente);
	
	public boolean occupa(Cliente cliente);
	
	// funge anche da notify
	public boolean rilascia(Cliente cliente);
	
	public int getPostiDisponibili();
	
	public Esercizio getEsercizio();
	
	public String getCodicePostazione();

}
