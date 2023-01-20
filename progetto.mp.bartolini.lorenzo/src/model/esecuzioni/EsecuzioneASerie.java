package model.esecuzioni;

import model.esercizi.Esercizio;
import model.visitors.VisitorEsecuzione;

public final class EsecuzioneASerie implements Esecuzione {

	private int serie;
	private int ripetizioniPerSerie;
	private Esercizio esercizio;
	private double peso;

	public EsecuzioneASerie(Esercizio esercizio, int serie, int ripetizioniPerSerie, double peso) {
		this.esercizio = esercizio;
		this.serie = serie;
		this.ripetizioniPerSerie = ripetizioniPerSerie;
		this.peso = peso;
	}

	@Override
	public Esercizio getEsercizio() {
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
