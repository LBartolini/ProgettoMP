package model.cliente;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import model.abbonamenti.Abbonamento;
import model.esercizi.EsercizioInterface;
import model.palestra.PalestraInterface;
import model.postazioni.PostazioneInterface;

public final class Cliente implements ClienteInterface {
	
	private String codiceCliente;
	private String nome, cognome;
	private int età;
	private double peso;
	private PalestraInterface palestra;
	private Abbonamento abbonamento;
	private Collection<PostazioneInterface> postazioniPrenotate;
	private Optional<PostazioneInterface> postazioneAttuale;

	public Cliente(PalestraInterface palestra, 
			Abbonamento abbonamento, 
			String codiceCliente, 
			Collection<PostazioneInterface> postazioniPrenotate,
			String nome, String cognome, int età, double peso) {
		
		this.palestra = palestra;
		this.abbonamento = abbonamento;
		this.codiceCliente = codiceCliente;
		this.postazioniPrenotate = postazioniPrenotate;
		this.postazioneAttuale = Optional.empty();
		this.nome = nome;
		this.cognome = cognome;
		this.età = età;
		this.peso = peso;
	}

	@Override
	public void notificaPostazioneLibera(PostazioneInterface postazione) {
		palestra.notifyClientePostazioneLibera(postazione.getCodicePostazione(), getCodiceCliente());
	}
	
	private Optional<PostazioneInterface> getPostazionefromCodice(String codice) {
		return postazioniPrenotate.stream()
			.filter(postazione -> postazione.getCodicePostazione().equals(codice))
			.findAny();
	}
	
	private Collection<PostazioneInterface> getPostazioniFromEsercizio(EsercizioInterface esercizio){
		return postazioniPrenotate.stream()
				.filter(postazione -> postazione.getEsercizio().equals(esercizio))
				.collect(Collectors.toList());
	}
	
	@Override
	public boolean occupaPostazione(String codicePostazione) {
		PostazioneInterface postazioneDaOccupare = getPostazionefromCodice(codicePostazione).orElseThrow();
		if(postazioneDaOccupare.occupa(this)) {
			postazioneAttuale = Optional.of(postazioneDaOccupare);
			//postazioniPrenotate.removeAll(getPostazioniFromEsercizio(postazioneDaOccupare.getEsercizio()));
			getPostazioniFromEsercizio(postazioneDaOccupare.getEsercizio())
				.forEach(postazione -> rimuoviPrenotazionePostazione(postazione.getCodicePostazione()));
			return true;
		}
		return false;
	}
	
	@Override
	public void prenotaPostazione(PostazioneInterface postazione) {
		postazioniPrenotate.add(postazione);
		postazione.prenota(this);
	}

	@Override
	public void rimuoviPrenotazionePostazione(String codicePostazione) {
		PostazioneInterface postazione = getPostazionefromCodice(codicePostazione).orElseThrow(); 
		postazioniPrenotate.remove(postazione);
		postazione.rimuoviPrenotazione(this);
	}
	
	@Override
	public void rilasciaPostazione(String codicePostazione) {
		postazioneAttuale.orElseThrow().rilascia(this);
		postazioneAttuale = Optional.empty();
	}
	
	@Override
	public String getCodiceCliente() {
		return codiceCliente;
	}
	
	@Override
	public Abbonamento getAbbonamento() {
		return abbonamento;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getCognome() {
		return cognome;
	}

	@Override
	public int getEtà() {
		return età;
	}

	@Override
	public double getPeso() {
		return peso;
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
