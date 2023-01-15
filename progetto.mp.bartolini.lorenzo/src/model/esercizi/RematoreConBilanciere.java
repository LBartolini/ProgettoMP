package model.esercizi;

import model.EsecuzioneEsercizioInterface;
import model.EsercizioInterface;
import model.VisitorEsercizioInterface;

public final class RematoreConBilanciere implements EsercizioInterface {

	private static final String NOME = "RematoreConBilanciere";
	private static final double COSTO_PER_RIPETIZIONE = 1.5;
	
	private static RematoreConBilanciere instance = null;

	public static RematoreConBilanciere getInstance() {
		if(instance == null)
			instance = new RematoreConBilanciere();
		
		return instance;
	}
	
	private RematoreConBilanciere() {
		
	}

	@Override
	public String getNomeEsercizio() {
		return NOME;
	}

	@Override
	public double getCosto(EsecuzioneEsercizioInterface esecuzione) {
		return COSTO_PER_RIPETIZIONE * esecuzione.getNumeroSerie() * esecuzione.getNumeroRipetizioni();
	}

	@Override
	public void acceptVisitor(VisitorEsercizioInterface visitor) {
		visitor.visitRematoreConBilanciere(this);
	}

}
