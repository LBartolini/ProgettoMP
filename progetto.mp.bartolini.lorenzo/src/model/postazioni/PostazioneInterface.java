package model.postazioni;

import model.ClienteInterface;
import model.esercizi.EsercizioInterface;

public interface PostazioneInterface {
	
	public void prenota(ClienteInterface cliente);
	
	public void rimuoviPrenotazione(ClienteInterface cliente);
	
	public boolean occupa(ClienteInterface cliente);
	
	public boolean rilascia(ClienteInterface cliente);
	
	public int getPostiDisponibili();
	
	public EsercizioInterface getEsercizio();
	
	public String getCodicePostazione();

}
