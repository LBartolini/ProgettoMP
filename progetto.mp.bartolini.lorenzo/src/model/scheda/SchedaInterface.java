package model.scheda;

import java.util.Collection;
import java.util.Optional;

import model.esecuzioni.EsecuzioneEsercizioInterface;
import model.esercizi.EsercizioInterface;

public interface SchedaInterface {

	public Collection<EsercizioInterface> getEsercizi();
	
	public Optional<EsecuzioneEsercizioInterface> getEsecuzioneEsercizio(EsercizioInterface esercizio);
	
	public double calcolaCosto();
	
	public double calcolaDifficoltà(double pesoCliente);
	
}
