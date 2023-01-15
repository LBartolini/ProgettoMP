package model.esercizi;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import model.Cliente;
import model.Esercizio;
import model.Palestra;
import model.VisitorEsercizio;

import java.util.Optional;

public abstract class EsercizioSingolo implements Esercizio {
	
	private Optional<Cliente> clienteAttuale;
	private Collection<Cliente> clientiInAttesa;
	private Palestra palestra;
	private String nomeEsercizio;

	public EsercizioSingolo(Palestra palestra, String nomeEsercizio) {
		this.palestra = palestra;
		this.nomeEsercizio = nomeEsercizio;
		clienteAttuale = Optional.empty();
		clientiInAttesa = new HashSet<>();
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
		clienteAttuale = Optional.of(cliente);
		return true;	
	}

	@Override
	public boolean rilascia(Cliente cliente) {
		if (!cliente.equals(clienteAttuale.get()))
			return false;
		clienteAttuale = Optional.empty();
		clientiInAttesa.forEach(c -> palestra.notifyClienteEsercizioLibero(this, c.getCodiceCliente()));
		return true;
	}

	@Override
	public int getPostiDisponibili() {
		return clienteAttuale.isEmpty() ? 0 : 1;
	}

	public String getNomeEsercizio() {
		return nomeEsercizio;
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
		EsercizioSingolo other = (EsercizioSingolo) obj;
		return Objects.equals(nomeEsercizio, other.nomeEsercizio);
	}

	@Override
	public void acceptVisitor(VisitorEsercizio visitor) {
		visitor.visitEsercizioSingolo(this);
	}
	
}
