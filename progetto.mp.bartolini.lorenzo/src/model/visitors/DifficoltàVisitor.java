package model.visitors;

import model.esecuzioni.EsecuzioneASerie;
import model.esecuzioni.EsecuzioneATempo;

public final class DifficoltàVisitor implements VisitorEsecuzione {

	private double pesoCliente;
	private double difficoltà;
	private int nEsercizi;

	public DifficoltàVisitor(double pesoCliente) {
		this.pesoCliente = pesoCliente;
		this.difficoltà = 0;
		this.nEsercizi = 0;
	}

	@Override
	public void visitEsecuzioneASerie(EsecuzioneASerie esecuzione) {
		nEsercizi++;
		difficoltà += esecuzione.getPeso() 
				* esecuzione.getSerie() 
				* esecuzione.getRipetizioniPerSerie();
	}

	@Override
	public void visitEsecuzioneATempo(EsecuzioneATempo esecuzione) {
		nEsercizi++;
		difficoltà += esecuzione.getIntensità() 
				* esecuzione.getMinuti() 
				* pesoCliente;
	}

	public double get() {
		return difficoltà/nEsercizi;
	}

}
