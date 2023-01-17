package model.esecuzioni;

import model.esercizi.EsercizioInterface;
import model.visitors.VisitorEsecuzione;

public final class EsecuzioneASerie implements EsecuzioneEsercizioInterface {

	private int serie;
	private int ripetizioniPerSerie;
	private EsercizioInterface esercizio;
	private double peso;

	public EsecuzioneASerie(EsercizioInterface esercizio, int serie, int ripetizioniPerSerie, double peso) {
		this.esercizio = esercizio;
		this.serie = serie;
		this.ripetizioniPerSerie = ripetizioniPerSerie;
		this.peso = peso;
	}

	@Override
	public EsercizioInterface getEsercizio() {
		return esercizio;
	}

	public int getSerie() {
		return serie;
	}

	public int getRipetizioniPerSerie() {
		return ripetizioniPerSerie;
	}
	
	public double getPeso() {
		return peso;
	}

	@Override
	public void accept(VisitorEsecuzione visitor) {
		visitor.visitEsecuzioneASerie(this);
	}

}
