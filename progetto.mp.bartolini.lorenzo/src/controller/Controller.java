package controller;

public interface Controller { 
	// facade per controller
	// si occupa di serializzare le richieste dei Client
	
	// notifica al cliente che il macchinario richiesto è libero
	public void notifyClientePostazioneLibera(String codicePostazione, String codiceCliente);
	
}
