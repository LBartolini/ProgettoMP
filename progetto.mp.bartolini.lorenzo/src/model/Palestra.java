package model;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import controller.Controller;
import database.Database;
import model.abbonamenti.Abbonamento;
import model.esercizi.Esercizio;
import model.postazioni.Postazione;

public final class Palestra {
	
	private String nome;
	private Controller controller;
	private Database database;
	private Collection<Postazione> postazioni;
	private Collection<Cliente> clientiInPalestra;

	public Palestra(String nome,
			Controller controller, 
			Database database, 
			Collection<Postazione> postazioni, 
			Collection<Cliente> clientiInPalestra) {
		
		this.nome = nome;
		this.postazioni = postazioni;
		this.clientiInPalestra = clientiInPalestra;
		this.controller = controller;
		this.database = database;
	}
	
	// package-private a fini di test
	Optional<Cliente> getClienteFromCodice(String codiceCliente) {
		return clientiInPalestra.stream()
			.filter(cliente -> cliente.getCodiceCliente().equals(codiceCliente))
			.findAny();
	}
	
	// package-private a fini di test
	Collection<Postazione> getPostazioniFromEsercizio(Esercizio esercizio){
		return postazioni.stream()
				.filter(postazione -> postazione.getEsercizio().equals(esercizio))
				.collect(Collectors.toList());
	}

	public boolean prenotaEserciziConScheda(Scheda scheda, String codiceCliente) {
		Collection<Esercizio> esercizi = scheda.getEsercizi();
		Cliente cliente = getClienteFromCodice(codiceCliente).orElseThrow();
		
		if(!esercizi.stream().allMatch(esercizio -> cliente.getAbbonamento().isEsercizioPermesso(esercizio)))
			return false;
		
		esercizi.forEach(esercizio -> {
			getPostazioniFromEsercizio(esercizio)
				.forEach(postazione -> cliente.prenotaPostazione(postazione));
		});
		
		return true;
	}

	public boolean prenotaEsercizio(Esercizio esercizio, String codiceCliente) {
		Cliente cliente = getClienteFromCodice(codiceCliente).orElseThrow();
		if(!cliente.getAbbonamento().isEsercizioPermesso(esercizio))
			return false;
		
		getPostazioniFromEsercizio(esercizio)
			.forEach(postazione -> cliente.prenotaPostazione(postazione));
		return true;
	}

	public void notifyClientePostazioneLibera(String codicePostazione, String codiceCliente) {
		controller.notifyClientePostazioneLibera(codicePostazione, codiceCliente);
	}

	public double getCostoScheda(Scheda scheda) {
		return scheda.calcolaCosto();
	}
	
	public String getInfoScheda(Scheda scheda) {
		return scheda.getInfo();
	}

	public double getPrezzoAbbonamento(Abbonamento abbonamento) {
		return abbonamento.getPrezzo();
	}

	public void acquistaAbbonamento(Abbonamento abbonamento, String codiceCliente) {
		database.setAbbonamentoCliente(abbonamento, codiceCliente);
	}

	public boolean accessoInPalestra(String codiceCliente) {
		Optional<Cliente> cliente = database.getCliente(this, codiceCliente);
		
		if(cliente.isEmpty())
			return false;
		
		clientiInPalestra.add(cliente.orElseThrow());
		return true;
	}

	public void uscitaDallaPalestra(String codiceCliente) {
		getClienteFromCodice(codiceCliente)
			.ifPresent(cliente -> clientiInPalestra.remove(cliente));
	}

	public String getNome() {
		return nome;
	}

	public void occupaPostazione(String codicePostazione, String codiceCliente) {
		getClienteFromCodice(codiceCliente).orElseThrow()
			.occupaPostazione(codicePostazione);
	}

	public void rilasciaPostazione(String codicePostazione, String codiceCliente) {
		getClienteFromCodice(codiceCliente).orElseThrow()
			.rilasciaPostazione(codicePostazione);
	}
	
	public void rimuoviPrenotazionePostazione(String codicePostazione, String codiceCliente) {
		getClienteFromCodice(codiceCliente).orElseThrow()
			.rimuoviPrenotazionePostazione(codicePostazione);
	}

}
