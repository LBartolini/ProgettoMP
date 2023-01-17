package database;

import java.util.Optional;

import model.Cliente;
import model.abbonamenti.Abbonamento;
import model.palestra.PalestraInterface;

public interface Database {
	
	public Optional<Cliente> getCliente(PalestraInterface palestra, String codiceCliente);
	
	public void setAbbonamentoCliente(Abbonamento abbonamento, String codiceCliente);

}
