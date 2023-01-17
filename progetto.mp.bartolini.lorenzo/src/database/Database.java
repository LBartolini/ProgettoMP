package database;

import java.util.Optional;

import model.abbonamenti.Abbonamento;
import model.cliente.Cliente;
import model.palestra.PalestraInterface;

public interface Database {
	
	public Optional<Cliente> getCliente(PalestraInterface palestra, String codiceCliente);
	
	public void setAbbonamentoCliente(Abbonamento abbonamento, String codiceCliente);

}
