package model.palestra;

import model.SchedaInterface;
import model.abbonamenti.Abbonamento;
import model.esercizi.EsercizioInterface;

public interface PalestraInterface {
	
	public String getNome();
	
	public boolean prenotaEserciziConScheda(SchedaInterface scheda, String codiceCliente);

	public boolean prenotaEsercizio(EsercizioInterface esercizio, String codiceCliente);
	
	public void rimuoviPrenotazionePostazione(String codicePostazione, String codiceCliente);
	
	public void occupaPostazione(String codicePostazione, String codiceCliente);
	
	public void rilasciaPostazione(String codicePostazione, String codiceCliente);
	
	public void notifyClientePostazioneLibera(String codicePostazione, String codiceCliente);

	public double getCostoScheda(SchedaInterface scheda);
	
	public double getDifficoltàScheda(SchedaInterface scheda, double pesoCliente);
	
	public double getPrezzoAbbonamento(Abbonamento abbonamento);
	
	public void acquistaAbbonamento(Abbonamento abbonamento, String codiceCliente);
	
	public boolean accessoInPalestra(String codiceCliente);

	public void uscitaDallaPalestra(String codiceCliente);

}
