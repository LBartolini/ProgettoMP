package model.esercizi;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import model.Cliente;
import model.Esercizio;
import model.PostazioneEsercizio;

import java.util.Optional;

public abstract class PostazioneEsercizioSingolo implements PostazioneEsercizio {
	
	private static int incrementale = 1;
	
	private Optional<Cliente> clienteAttuale;
	private Collection<Cliente> clientiInAttesa;
	private Esercizio esercizio;
	private int id;

	protected PostazioneEsercizioSingolo(Esercizio esercizio) {
		this.esercizio = esercizio;
		clienteAttuale = Optional.empty();
		clientiInAttesa = new HashSet<>();
		this.id = incrementale++;
	}

	@Override
	public void prenota(Cliente cliente) {
		clientiInAttesa.add(cliente);
		
		if(getPostiDisponibili()>0)
			cliente.notificaPostazioneLibera(getCodicePostazione());
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
		clienteAttuale = Optional.of(cliente);
		return true;	
	}

	@Override
	public boolean rilascia(Cliente cliente) {
		if (!cliente.equals(clienteAttuale.get()))
			return false;
		clienteAttuale = Optional.empty();
		clientiInAttesa.forEach(c -> c.notificaPostazioneLibera(getCodicePostazione()));
		return true;
	}

	@Override
	public int getPostiDisponibili() {
		return clienteAttuale.isEmpty() ? 0 : 1;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		return false;
	}
	
}
