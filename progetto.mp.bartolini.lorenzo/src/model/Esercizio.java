package model;

public interface Esercizio {

	public void prenota(Cliente cliente);
	
	public boolean occupa(Cliente cliente);
	
	// funge anche da notify
	public boolean rilascia(Cliente cliente);
	
	public int getPostiDisponibili();
	
}
