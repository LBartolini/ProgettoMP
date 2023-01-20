package model.esecuzioni;

import model.esercizi.Esercizio;
import model.visitors.VisitorEsecuzione;

public interface Esecuzione {
	
	public Esercizio getEsercizio();
	
	public void accept(VisitorEsecuzione visitor);

}
