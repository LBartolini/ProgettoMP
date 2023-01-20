package model.postazioni;

import java.util.Collection;
import java.util.Objects;

import model.Cliente;
import model.esercizi.Esercizio;

public final class PostazioneMultipla implements Postazione {
	
	private static int incrementale = 1;
	
	private Esercizio esercizio;
	private int id;
	private int maxClientiInContemporanea;
	Collection<Cliente> clientiAttuali; // package private ai fini di test
	Collection<Cliente> clientiInAttesa; // package private per fini di test

	public PostazioneMultipla(Esercizio esercizio, int maxClientiInContemporanea,
			Collection<Cliente> clientiAttuali, Collection<Cliente> clientiInAttesa) {
		
		this.esercizio = esercizio;
		this.maxClientiInContemporanea = maxClientiInContemporanea;
		this.clientiAttuali = clientiAttuali;
		this.clientiInAttesa = clientiInAttesa;
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
		
		cliente.rimuoviPrenotazionePostazione(getCodicePostazione());
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
	public Esercizio getEsercizio() {
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

}
