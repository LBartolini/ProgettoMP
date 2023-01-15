package model;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cliente {
	
	private String codiceCliente;
	private PalestraInterface palestra;
	private Abbonamento abbonamento;
	private Collection<PostazioneInterface> postazioniPrenotate;
	private Optional<PostazioneInterface> postazioneAttuale;

	public Cliente(PalestraInterface palestra, 
			Abbonamento abbonamento, 
			String codiceCliente, 
			Collection<PostazioneInterface> postazioniPrenotate) {
		
		this.palestra = palestra;
		this.abbonamento = abbonamento;
		this.codiceCliente = codiceCliente;
		this.postazioniPrenotate = postazioniPrenotate;
		this.postazioneAttuale = Optional.empty();
	}

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
	
	public void occupaPostazione(String codicePostazione) {
		PostazioneInterface postazioneDaOccupare = getPostazionefromCodice(codicePostazione).orElseThrow();
		if(postazioneDaOccupare.occupa(this)) {
			postazioneAttuale = Optional.of(postazioneDaOccupare);
			postazioniPrenotate.remove(postazioneDaOccupare);
			postazioniPrenotate.removeAll(getPostazioniFromEsercizio(postazioneDaOccupare.getEsercizio()));
		}
	}
	
	public void rilasciaPostazione(String codicePostazione) {
		postazioneAttuale.orElseThrow().rilascia(this);
	}
	
	public String getCodiceCliente() {
		return codiceCliente;
	}
	
	public Abbonamento getAbbonamento() {
		return abbonamento;
	}
	
}
