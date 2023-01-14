package database;

import java.util.Optional;

import model.Abbonamento;
import model.Cliente;

public interface Database {
	
	// cerca nel database un cliente con il codice specificato e ne restituisce l'oggetto Cliente
	// altrimenti nulla
	public Optional<Cliente> getCliente(String codiceCliente);
	
	// imposta l'abbonamento al cliente
	public void setAbbonamentoCliente(Abbonamento abbonamento, String codiceCliente);

}
