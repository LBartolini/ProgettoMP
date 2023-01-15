package database;

import java.util.Optional;

import model.Abbonamento;
import model.Cliente;
import model.PalestraInterface;

public interface Database {
	
	// cerca nel database un cliente con il codice specificato e ne restituisce l'oggetto Cliente
	// altrimenti nulla
	public Optional<Cliente> getCliente(PalestraInterface palestra, String codiceCliente);
	
	// imposta l'abbonamento al cliente
	public void setAbbonamentoCliente(Abbonamento abbonamento, String codiceCliente);

}
