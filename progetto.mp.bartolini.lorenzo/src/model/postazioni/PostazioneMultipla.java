package model.postazioni;

import java.util.Collection;
import java.util.Objects;

import model.ClienteInterface;
import model.esercizi.EsercizioInterface;

public final class PostazioneMultipla implements PostazioneInterface {
	
	private static int incrementale = 1;
	
	private EsercizioInterface esercizio;
	private int id;
	private int maxClientiInContemporanea;
	private Collection<ClienteInterface> clientiAttuali;
	private Collection<ClienteInterface> clientiInAttesa;

	public PostazioneMultipla(EsercizioInterface esercizio, int maxClientiInContemporanea,
			Collection<ClienteInterface> clientiAttuali, Collection<ClienteInterface> clientiInAttesa) {
		
		this.esercizio = esercizio;
		this.maxClientiInContemporanea = maxClientiInContemporanea;
		this.clientiAttuali = clientiAttuali;
		this.clientiInAttesa = clientiInAttesa;
		this.id = incrementale++;
	}

	@Override
	public void prenota(ClienteInterface cliente) {
		clientiInAttesa.add(cliente);
		
		if(getPostiDisponibili()>0)
			cliente.notificaPostazioneLibera(this);
	}
	
	@Override
	public void rimuoviPrenotazione(ClienteInterface cliente) {
		clientiInAttesa.remove(cliente);
	}

	@Override
	public boolean occupa(ClienteInterface cliente) {
		if(getPostiDisponibili() == 0)
			return false;
		
		if(!clientiInAttesa.contains(cliente))
			return false;
		
		rimuoviPrenotazione(cliente);
		clientiAttuali.add(cliente);
		return true;
		
	}

	@Override
	public boolean rilascia(ClienteInterface cliente) {
		if(!clientiAttuali.contains(cliente))
			return false;
		
		clientiAttuali.remove(cliente);
		clientiInAttesa.forEach(c -> c.notificaPostazioneLibera(this));
		return true;
	}

	@Override
	public int getPostiDisponibili() {
		return maxClientiInContemporanea - clientiAttuali.size();
	}
	
	@Override
	public EsercizioInterface getEsercizio() {
		return esercizio;
	}
	
	@Override
	public String getCodicePostazione() {
		return "M"+id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCodicePostazione());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		return false;
	}

}
