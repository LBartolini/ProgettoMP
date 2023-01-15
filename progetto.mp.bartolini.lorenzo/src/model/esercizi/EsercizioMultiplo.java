package model.esercizi;

import java.util.Collection;
import java.util.Objects;

import model.Cliente;
import model.Esercizio;
import model.Palestra;
import model.VisitorEsercizio;

public abstract class EsercizioMultiplo implements Esercizio {
	
	
	private Palestra palestra;
	private String nomeEsercizio;
	private int maxClientiInContemporanea;
	
	private Collection<Cliente> clientiAttuali;
	private Collection<Cliente> clientiInAttesa;

	public EsercizioMultiplo(Palestra palestra, String nomeEsercizio, int maxClientiInContemporanea) {
		this.palestra = palestra;
		this.nomeEsercizio = nomeEsercizio;
		this.maxClientiInContemporanea = maxClientiInContemporanea;
		
	}

	@Override
	public void prenota(Cliente cliente) {
		clientiInAttesa.add(cliente);
		
		if(getPostiDisponibili()>0)
			palestra.notifyClienteEsercizioLibero(this, cliente.getCodiceCliente());
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
		clientiInAttesa.forEach(c -> palestra.notifyClienteEsercizioLibero(this, c.getCodiceCliente()));
		return true;
	}

	@Override
	public int getPostiDisponibili() {
		return maxClientiInContemporanea - clientiAttuali.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomeEsercizio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EsercizioMultiplo other = (EsercizioMultiplo) obj;
		return Objects.equals(nomeEsercizio, other.nomeEsercizio);
	}

	@Override
	public void acceptVisitor(VisitorEsercizio visitor) {
		visitor.visitEsercizioMultiplo(this);
	}
	
	

}
