package model.esercizi;

import java.util.Collection;
import java.util.HashSet;

import model.Cliente;
import model.Esercizio;
import model.Palestra;

import java.util.Optional;

public final class EsercizioSingolo implements Esercizio {
	
	private Optional<Cliente> clienteAttuale;
	private Collection<Cliente> clientiInAttesa;
	private Palestra palestra;
	
	public EsercizioSingolo(Palestra palestra) {
		this.palestra = palestra;
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
	public boolean occupa(Cliente cliente) {
		if(getPostiDisponibili() == 0)
			return false;
		
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
	
}
