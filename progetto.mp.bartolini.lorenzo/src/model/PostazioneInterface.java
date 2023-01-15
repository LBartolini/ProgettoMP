package model;

public interface PostazioneInterface {
	
	public void prenota(Cliente cliente);
	
	public void rimuoviPrenotazione(Cliente cliente);
	
	public boolean occupa(Cliente cliente);
	
	public boolean rilascia(Cliente cliente);
	
	public int getPostiDisponibili();
	
	public EsercizioInterface getEsercizio();
	
	public String getCodicePostazione();

}
