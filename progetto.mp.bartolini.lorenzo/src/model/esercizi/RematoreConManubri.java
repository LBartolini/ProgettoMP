package model.esercizi;

import model.EsecuzioneEsercizioInterface;
import model.EsercizioInterface;
import model.VisitorEsercizioInterface;

public final class RematoreConManubri implements EsercizioInterface {

	private static final String NOME = "RematoreConManubri";
	private static final int COSTO_PER_RIPETIZIONE = 1;
	
	private static RematoreConManubri instance = null;

	public static RematoreConManubri getInstance() {
		if(instance == null)
			instance = new RematoreConManubri();
		
		return instance;
	}
	
	private RematoreConManubri() {
		
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
		visitor.visitRematoreConManubri(this);
	}

}
