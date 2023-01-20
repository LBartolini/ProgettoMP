package model.postazioni;

import java.util.Collection;
import java.util.Objects;

import model.Cliente;
import model.esercizi.Esercizio;

import java.util.Optional;

public final class PostazioneSingola implements Postazione {
	
	private static int incrementale = 1;
	
	Optional<Cliente> clienteAttuale; // package private ai fini di test
	Collection<Cliente> clientiInAttesa; // package private ai fini di test
	private Esercizio esercizio;
	private int id;

	public PostazioneSingola(Esercizio esercizio, Collection<Cliente> clientiInAttesa) {
		this.esercizio = esercizio;
		clienteAttuale = Optional.empty();
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
		clienteAttuale = Optional.of(cliente);
		return true;	
	}

	@Override
	public boolean rilascia(Cliente cliente) {
		if(clienteAttuale.isEmpty())
			return false;
		if (!clienteAttuale.orElseThrow().equals(cliente))
			return false;
		
		clienteAttuale = Optional.empty();
		clientiInAttesa.forEach(c -> c.notificaPostazioneLibera(this));
		return true;
	}

	@Override
	public int getPostiDisponibili() {
		return clienteAttuale.isEmpty() ? 1 : 0;
	}

	@Override
	public Esercizio getEsercizio() {
		return esercizio;
	}
	
	@Override
	public String getCodicePostazione() {
		return "S"+id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getCodicePostazione());
	}
	
}
