package model.visitors;

import model.esercizi.RematoreConBilanciere;
import model.SchedaInterface;
import model.VisitorEsercizioInterface;
import model.esercizi.PancaPiana;
import model.esercizi.RematoreConManubri;

public final class CostoVisitor implements VisitorEsercizioInterface {

	private double costoTotale;
	private SchedaInterface scheda;
	
	public CostoVisitor(SchedaInterface scheda) {
		this.scheda = scheda;
		costoTotale = 0;
	}
	
	public double getCostoTotale() {
		return costoTotale;
	}

	@Override
	public void visitPancaPiana(PancaPiana esercizio) {
		costoTotale += 10 + esercizio.getCosto(scheda.getEsecuzioneEsercizio(esercizio).orElseThrow());		
	}

	@Override
	public void visitRematoreConManubri(RematoreConManubri esercizio) {
		costoTotale += esercizio.getCosto(scheda.getEsecuzioneEsercizio(esercizio).orElseThrow());
	}

	@Override
	public void visitRematoreConBilanciere(RematoreConBilanciere esercizio) {
		costoTotale += 2 * esercizio.getCosto(scheda.getEsecuzioneEsercizio(esercizio).orElseThrow());
	}

}
