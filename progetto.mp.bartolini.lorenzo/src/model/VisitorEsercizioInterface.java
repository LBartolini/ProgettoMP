package model;

import model.esercizi.PancaPiana;
import model.esercizi.RematoreConBilanciere;
import model.esercizi.RematoreConManubri;

public interface VisitorEsercizioInterface {

	public void visitPancaPiana(PancaPiana esercizio);

	public void visitRematoreConManubri(RematoreConManubri esercizio);
	
	public void visitRematoreConBilanciere(RematoreConBilanciere esercizio);
	
}
