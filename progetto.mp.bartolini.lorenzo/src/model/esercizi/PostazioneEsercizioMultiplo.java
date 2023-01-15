package model.esercizi;

import java.util.Collection;
import java.util.Objects;

import model.Cliente;
import model.Esercizio;
import model.PostazioneEsercizio;

public abstract class PostazioneEsercizioMultiplo implements PostazioneEsercizio {
	
	private static int incrementale = 1;
	
	private Esercizio esercizio;
	private int id;
	private int maxClientiInContemporanea;
	private Collection<Cliente> clientiAttuali;
	private Collection<Cliente> clientiInAttesa;

	protected PostazioneEsercizioMultiplo(Esercizio esercizio, int maxClientiInContemporanea) {
		this.esercizio = esercizio;
		this.maxClientiInContemporanea = maxClientiInContemporanea;
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
		clientiAttuali.add(cliente);
		return true;
		
	}

	@Override
	public boolean rilascia(Cliente cliente) {
		if(!clientiAttuali.contains(cliente))
			return false;
		
		clientiAttuali.remove(cliente);
		clientiInAttesa.forEach(c -> c.notificaPostazioneLibera(getCodicePostazione()));
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		return false;
	}

}
