package model.postazioni;

import java.util.Collection;
import java.util.Objects;

import model.Cliente;
import model.EsercizioInterface;
import model.PostazioneInterface;

public class PostazioneMultipla implements PostazioneInterface {
	
	private static int incrementale = 1;
	
	private EsercizioInterface esercizio;
	private int id;
	private int maxClientiInContemporanea;
	private Collection<Cliente> clientiAttuali;
	private Collection<Cliente> clientiInAttesa;

	protected PostazioneMultipla(EsercizioInterface esercizio, int maxClientiInContemporanea) {
		this.esercizio = esercizio;
		this.maxClientiInContemporanea = maxClientiInContemporanea;
		this.id = incrementale++;
	}

	@Override
	public void prenota(Cliente cliente) {
		clientiInAttesa.add(cliente);
		
		if(getPostiDisponibili()>0)
			cliente.notificaPostazioneLibera(this);
	}
	
	@Override
	public void rimuoviPrenotazione(Cliente cliente) {
		clientiInAttesa.remove(cliente);
	}

	@Override
	public boolean occupa(Cliente cliente) {
		if(getPostiDisponibili() == 0)
			return false;
		
		if(!clientiInAttesa.contains(cliente))
			return false;
		
		rimuoviPrenotazione(cliente);
		clientiAttuali.add(cliente);
		return true;
		
	}

	@Override
	public boolean rilascia(Cliente cliente) {
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
