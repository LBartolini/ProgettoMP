package model;

import model.esercizi.EsercizioMultiplo;
import model.esercizi.EsercizioSingolo;

public interface VisitorEsercizio {
	
	public void visitEsercizioSingolo(EsercizioSingolo esercizio);
	
	public void visitEsercizioMultiplo(EsercizioMultiplo esercizio);

}
