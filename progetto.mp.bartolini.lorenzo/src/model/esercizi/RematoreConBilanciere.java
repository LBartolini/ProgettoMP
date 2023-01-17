package model.esercizi;

public final class RematoreConBilanciere implements EsercizioInterface {

	private static final String NOME = "RematoreConBilanciere";
	private static final double COSTO_PER_RIPETIZIONE = 0.4;
	private static final double COSTO_AL_MINUTO = 0.8;
	
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
	public double getCostoPerRipetizione() {
		return COSTO_PER_RIPETIZIONE;
	}

	@Override
	public double getCostoAlMinuto() {
		return COSTO_AL_MINUTO;
	}

}
