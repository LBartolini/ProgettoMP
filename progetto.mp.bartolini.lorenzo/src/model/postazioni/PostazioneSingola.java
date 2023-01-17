package model.postazioni;

import java.util.Collection;
import java.util.Objects;

import model.cliente.ClienteInterface;
import model.esercizi.EsercizioInterface;

import java.util.Optional;

public final class PostazioneSingola implements PostazioneInterface {
	
	private static int incrementale = 1;
	
	private Optional<ClienteInterface> clienteAttuale;
	Collection<ClienteInterface> clientiInAttesa; // package private ai fini di test
	private EsercizioInterface esercizio;
	private int id;

	public PostazioneSingola(EsercizioInterface esercizio, Collection<ClienteInterface> clientiInAttesa) {
		this.esercizio = esercizio;
		clienteAttuale = Optional.empty();
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
		
		cliente.rimuoviPrenotazionePostazione(getCodicePostazione());
		rimuoviPrenotazione(cliente);
		clienteAttuale = Optional.of(cliente);
		return true;	
	}

	@Override
	public boolean rilascia(ClienteInterface cliente) {
		if (!cliente.equals(clienteAttuale.get()))
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
	public EsercizioInterface getEsercizio() {
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
