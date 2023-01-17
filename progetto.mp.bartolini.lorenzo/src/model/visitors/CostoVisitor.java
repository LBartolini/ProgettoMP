package model.visitors;

import model.esecuzioni.EsecuzioneASerie;
import model.esecuzioni.EsecuzioneATempo;

public final class CostoVisitor implements VisitorEsecuzione {

	private double costoTotale;
	
	public CostoVisitor() {
		costoTotale = 0;
	}
	
	public double get() {
		return costoTotale;
	}

	@Override
	public void visitEsecuzioneASerie(EsecuzioneASerie esecuzione) {
		costoTotale += esecuzione.getSerie() 
				* esecuzione.getRipetizioniPerSerie() 
				* esecuzione.getEsercizio().getCostoPerRipetizione();
	}

	@Override
	public void visitEsecuzioneATempo(EsecuzioneATempo esecuzione) {
		costoTotale += esecuzione.getMinuti()
				* esecuzione.getEsercizio().getCostoAlMinuto();
	}

	

}
