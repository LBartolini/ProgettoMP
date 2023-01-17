package model.esecuzioni;

import model.esercizi.EsercizioInterface;
import model.visitors.VisitorEsecuzione;

public interface EsecuzioneEsercizioInterface {
	
	public EsercizioInterface getEsercizio();
	
	public void accept(VisitorEsecuzione visitor);

}
