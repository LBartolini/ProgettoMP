package model.postazioni;

import model.Cliente;
import model.esercizi.Esercizio;

public interface Postazione {
	
	public void prenota(Cliente cliente);
	
	public void rimuoviPrenotazione(Cliente cliente);
	
	public boolean occupa(Cliente cliente);
	
	public boolean rilascia(Cliente cliente);
	
	public int getPostiDisponibili();
	
	public Esercizio getEsercizio();
	
	public String getCodicePostazione();

}
