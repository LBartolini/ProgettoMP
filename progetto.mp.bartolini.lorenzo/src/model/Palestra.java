package model;

public interface Palestra {
	// facade per model
	
	// interazione con Database per prendere informazioni riguardanti clienti
	// metodo per ottenere l'oggetto Cliente dal codiceCliente
	
	// prenota tutti i macchinari fornendo una scheda che dovrà prima essere convalidata da Palestra
	// resituisce true se la scheda è stata approvata, false altrimenti
	public boolean prenotaEserciziConScheda(Scheda scheda, String codiceCliente);

	// chiedi a Palestra di prenotare un macchinario a nome del cliente passato come
	// parametro
	public void prenotaEsercizio(Esercizio esercizio, String codiceCliente);
	
	// notifica al cliente che il macchinario richiesto è libero
	// comunica a Controller la richiesta
	public void notifyClientePostazioneLibera(String codicePostazione, String codiceCliente);

	// calcolare costo della scheda in base ai macchinari utilizzati all'interno e all'esecuzione
	public double getCostoScheda(Scheda scheda);
	
	// calcola il costo dell'abbonamento
	public double getPrezzoAbbonamento(Abbonamento abbonamento);
	
	// imposta nel database, e nell'eventuale oggetto, il nuovo abbonamento al cliente specificato
	public void acquistaAbbonamento(Abbonamento abbonamento, String codiceCliente);
	
	// chiedere alla classe Palestra di tentare di far accedere il cliente con il
	// codiceCliente
	// passato come parametro, Palestra chiederà le informazioni a Database, se ci
	// sono
	// restituisce true se l'accesso è consentito, falso altrimenti
	public boolean accessoInPalestra(String codiceCliente);

	// notifica la palestra che l'utente è uscito
	// rimuovi il cliente da tutti i macchinari che osservava
	public void uscitaDallaPalestra(String codiceCliente);

}
