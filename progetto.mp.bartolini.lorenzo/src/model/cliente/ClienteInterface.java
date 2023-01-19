package model.cliente;

import model.abbonamenti.Abbonamento;
import model.postazioni.PostazioneInterface;

public interface ClienteInterface {

	public void notificaPostazioneLibera(PostazioneInterface postazione);

	public boolean occupaPostazione(String codicePostazione);

	public void rilasciaPostazione(String codicePostazione);
	
	public void prenotaPostazione(PostazioneInterface postazione);
	
	public void rimuoviPrenotazionePostazione(String codicePostazione);

	public String getCodiceCliente();

	public Abbonamento getAbbonamento();

	public String getNome();

	public String getCognome();

	public int getEtà();

	public double getPeso();

}