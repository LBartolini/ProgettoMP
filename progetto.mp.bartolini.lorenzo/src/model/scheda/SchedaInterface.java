package model.scheda;

import java.util.Collection;

import model.esecuzioni.EsecuzioneEsercizioInterface;
import model.esercizi.EsercizioInterface;

public interface SchedaInterface {

	public Collection<EsercizioInterface> getEsercizi();
	
	public Collection<EsecuzioneEsercizioInterface> getEsecuzioniFromEsercizio(EsercizioInterface esercizio);
	
	public double calcolaCosto();

	public String getInfo();
	
}
