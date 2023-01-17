package model.palestra;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import controller.Controller;
import database.Database;
import model.Cliente;
import model.ClienteInterface;
import model.SchedaInterface;
import model.abbonamenti.Abbonamento;
import model.esercizi.EsercizioInterface;
import model.postazioni.PostazioneInterface;

public final class Palestra implements PalestraInterface {
	
	private String nome;
	private Controller controller;
	private Database database;
	private Collection<PostazioneInterface> postazioni;
	private Collection<ClienteInterface> clientiInPalestra;

	public Palestra(String nome,
			Controller controller, 
			Database database, 
			Collection<PostazioneInterface> postazioni, 
			Collection<ClienteInterface> clientiInPalestra) {
		
		this.nome = nome;
		this.postazioni = postazioni;
		this.clientiInPalestra = clientiInPalestra;
		this.controller = controller;
		this.database = database;
	}
	
	// package-private a fini di test
	Optional<ClienteInterface> getClienteFromCodice(String codiceCliente) {
		return clientiInPalestra.stream()
			.filter(cliente -> cliente.getCodiceCliente().equals(codiceCliente))
			.findAny();
	}
	
	// package-private a fini di test
	Collection<PostazioneInterface> getPostazioniFromEsercizio(EsercizioInterface esercizio){
		return postazioni.stream()
				.filter(postazione -> postazione.getEsercizio().equals(esercizio))
				.collect(Collectors.toList());
	}

	@Override
	public boolean prenotaEserciziConScheda(SchedaInterface scheda, String codiceCliente) {
		Collection<EsercizioInterface> esercizi = scheda.getEsercizi();
		ClienteInterface cliente = getClienteFromCodice(codiceCliente).orElseThrow();
		
		if(!esercizi.stream().allMatch(esercizio -> cliente.getAbbonamento().isEsercizioPermesso(esercizio)))
			return false;
		
		esercizi.forEach(esercizio -> {
			getPostazioniFromEsercizio(esercizio)
				.forEach(postazione -> cliente.prenotaPostazione(postazione));
		});
		
		return true;
	}

	@Override
	public boolean prenotaEsercizio(EsercizioInterface esercizio, String codiceCliente) {
		ClienteInterface cliente = getClienteFromCodice(codiceCliente).orElseThrow();
		if(!cliente.getAbbonamento().isEsercizioPermesso(esercizio))
			return false;
		
		getPostazioniFromEsercizio(esercizio)
			.forEach(postazione -> cliente.prenotaPostazione(postazione));
		return true;
	}

	@Override
	public void notifyClientePostazioneLibera(String codicePostazione, String codiceCliente) {
		controller.notifyClientePostazioneLibera(codicePostazione, codiceCliente);
	}

	@Override
	public double getCostoScheda(SchedaInterface scheda) {
		return scheda.calcolaCosto();
	}
	
	@Override
	public double getDifficoltàScheda(SchedaInterface scheda, double pesoCliente) {
		return scheda.calcolaDifficoltà(pesoCliente);
	}

	@Override
	public double getPrezzoAbbonamento(Abbonamento abbonamento) {
		return abbonamento.getPrezzo();
	}

	@Override
	public void acquistaAbbonamento(Abbonamento abbonamento, String codiceCliente) {
		database.setAbbonamentoCliente(abbonamento, codiceCliente);
	}

	@Override
	public boolean accessoInPalestra(String codiceCliente) {
		Optional<Cliente> cliente = database.getCliente(this, codiceCliente);
		
		if(cliente.isEmpty())
			return false;
		
		clientiInPalestra.add(cliente.orElseThrow());
		return true;
	}

	@Override
	public void uscitaDallaPalestra(String codiceCliente) {
		getClienteFromCodice(codiceCliente)
			.ifPresent(cliente -> clientiInPalestra.remove(cliente));
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void occupaPostazione(String codicePostazione, String codiceCliente) {
		getClienteFromCodice(codiceCliente).orElseThrow()
			.occupaPostazione(codicePostazione);
	}

	@Override
	public void rilasciaPostazione(String codicePostazione, String codiceCliente) {
		getClienteFromCodice(codiceCliente).orElseThrow()
			.rilasciaPostazione(codicePostazione);
	}
	
	
	@Override
	public void rimuoviPrenotazionePostazione(String codicePostazione, String codiceCliente) {
		getClienteFromCodice(codiceCliente).orElseThrow()
			.rimuoviPrenotazionePostazione(codicePostazione);
	}

}
