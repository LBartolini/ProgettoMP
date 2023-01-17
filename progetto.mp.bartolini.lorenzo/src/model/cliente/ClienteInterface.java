package model.cliente;

import model.abbonamenti.Abbonamento;
import model.postazioni.PostazioneInterface;

public interface ClienteInterface {

	void notificaPostazioneLibera(PostazioneInterface postazione);

	boolean occupaPostazione(String codicePostazione);

	void rilasciaPostazione(String codicePostazione);
	
	void prenotaPostazione(PostazioneInterface postazione);
	
	void rimuoviPrenotazionePostazione(String codicePostazione);

	String getCodiceCliente();

	Abbonamento getAbbonamento();

	String getNome();

	String getCognome();

	int getEtà();

	double getPeso();

}