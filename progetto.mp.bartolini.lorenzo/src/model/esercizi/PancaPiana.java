package model.esercizi;

import model.EsecuzioneEsercizioInterface;
import model.EsercizioInterface;
import model.VisitorEsercizioInterface;

public final class PancaPiana implements EsercizioInterface {
	
	private static final String NOME = "PancaPiana";
	private static final int COSTO_PER_RIPETIZIONE = 3;
	
	private static PancaPiana instance = null;

	public static PancaPiana getInstance() {
		if(instance == null)
			instance = new PancaPiana();
		
		return instance;
	}
	
	private PancaPiana() {
		
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
		visitor.visitPancaPiana(this);
	}

}
