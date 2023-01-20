package database;

import java.util.Optional;

import model.Cliente;
import model.Palestra;
import model.abbonamenti.Abbonamento;

public interface Database {
	
	public Optional<Cliente> getCliente(Palestra palestra, String codiceCliente);
	
	public void setAbbonamentoCliente(Abbonamento abbonamento, String codiceCliente);

}
