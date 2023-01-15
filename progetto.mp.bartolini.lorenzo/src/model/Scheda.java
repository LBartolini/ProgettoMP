package model;

import java.util.Collection;
import java.util.Optional;

public interface Scheda {

	public Collection<String> getEsercizi();
	
	public Optional<EsecuzioneEsercizio> getEsecuzione(String nomeEsercizio);
	
	public double calcolaCosto();
	
}
