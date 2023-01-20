package model;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import model.abbonamenti.Abbonamento;
import model.esercizi.Esercizio;
import model.postazioni.Postazione;

public final class Cliente {
	
	private String codiceCliente;
	private String nome, cognome;
	private Palestra palestra;
	private Abbonamento abbonamento;
	private Collection<Postazione> postazioniPrenotate;
	private Optional<Postazione> postazioneAttuale;

	public Cliente(Palestra palestra, 
			Abbonamento abbonamento, 
			String codiceCliente, 
			Collection<Postazione> postazioniPrenotate,
			String nome, String cognome) {
		
		this.palestra = palestra;
		this.abbonamento = abbonamento;
		this.codiceCliente = codiceCliente;
		this.postazioniPrenotate = postazioniPrenotate;
		this.postazioneAttuale = Optional.empty();
		this.nome = nome;
		this.cognome = cognome;
	}

	public void notificaPostazioneLibera(Postazione postazione) {
		palestra.notifyClientePostazioneLibera(postazione.getCodicePostazione(), getCodiceCliente());
	}
	
	private Optional<Postazione> getPostazionefromCodice(String codice) {
		return postazioniPrenotate.stream()
			.filter(postazione -> postazione.getCodicePostazione().equals(codice))
			.findAny();
	}
	
	private Collection<Postazione> getPostazioniFromEsercizio(Esercizio esercizio){
		return postazioniPrenotate.stream()
				.filter(postazione -> postazione.getEsercizio().equals(esercizio))
				.collect(Collectors.toList());
	}
	
	public boolean occupaPostazione(String codicePostazione) {
		Postazione postazioneDaOccupare = getPostazionefromCodice(codicePostazione).orElseThrow();
		if(postazioneDaOccupare.occupa(this)) {
			postazioneAttuale = Optional.of(postazioneDaOccupare);
			getPostazioniFromEsercizio(postazioneDaOccupare.getEsercizio())
				.forEach(postazione -> rimuoviPrenotazionePostazione(postazione.getCodicePostazione()));
			return true;
		}
		return false;
	}
	
	public void prenotaPostazione(Postazione postazione) {
		if(!postazioniPrenotate.contains(postazione)) {
			postazioniPrenotate.add(postazione);
			postazione.prenota(this);
		}	
	}

	public void rimuoviPrenotazionePostazione(String codicePostazione) {
		Postazione postazione = getPostazionefromCodice(codicePostazione).orElseThrow(); 
		postazioniPrenotate.remove(postazione);
		postazione.rimuoviPrenotazione(this);
	}
	
	public void rilasciaPostazione(String codicePostazione) {
		postazioneAttuale.orElseThrow().rilascia(this);
		postazioneAttuale = Optional.empty();
	}
	
	public String getCodiceCliente() {
		return codiceCliente;
	}
	
	public Abbonamento getAbbonamento() {
		return abbonamento;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(codiceCliente, other.codiceCliente);
	}
	
}
