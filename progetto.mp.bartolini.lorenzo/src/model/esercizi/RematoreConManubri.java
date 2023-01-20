package model.esercizi;

public final class RematoreConManubri implements Esercizio {

	private static final String NOME = "RematoreConManubri";
	private static final double COSTO_PER_RIPETIZIONE = 0.25;
	private static final double COSTO_AL_MINUTO = 1;
	
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
	public double getCostoPerRipetizione() {
		return COSTO_PER_RIPETIZIONE;
	}
	
	@Override
	public double getCostoAlMinuto() {
		return COSTO_AL_MINUTO;
	}
	
}
