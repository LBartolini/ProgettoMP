package model;

import model.esercizi.PostazioneEsercizioMultiplo;
import model.esercizi.PostazioneEsercizioSingolo;

public interface VisitorEsercizio {
	
	public void visitEsercizioSingolo(PostazioneEsercizioSingolo esercizio);
	
	public void visitEsercizioMultiplo(PostazioneEsercizioMultiplo esercizio);

}
