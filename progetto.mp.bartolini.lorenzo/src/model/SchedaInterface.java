package model;

import java.util.Collection;
import java.util.Optional;

public interface SchedaInterface {

	public Collection<EsercizioInterface> getEsercizi();
	
	public Optional<EsecuzioneEsercizioInterface> getEsecuzioneEsercizio(EsercizioInterface esercizio);
	
	public double calcolaCosto();
	
	public int calcolaDifficoltà();
	
}
