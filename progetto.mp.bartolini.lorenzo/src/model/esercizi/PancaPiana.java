package model.esercizi;

public final class PancaPiana implements EsercizioInterface {
	
	private static final String NOME = "PancaPiana";
	private static final double COSTO_PER_RIPETIZIONE = 0.5;
	private static final double COSTO_AL_MINUTO = 1.5;
	
	
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
	public double getCostoPerRipetizione() {
		return COSTO_PER_RIPETIZIONE;
	}

	@Override
	public double getCostoAlMinuto() {
		return COSTO_AL_MINUTO;
	}
	
}
