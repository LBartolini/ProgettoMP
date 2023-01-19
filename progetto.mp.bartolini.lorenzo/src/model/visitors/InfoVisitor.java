package model.visitors;

import model.esecuzioni.EsecuzioneASerie;
import model.esecuzioni.EsecuzioneATempo;

public final class InfoVisitor implements VisitorEsecuzione {

	private StringBuilder info;

	public InfoVisitor() {
		info = new StringBuilder();
	}

	@Override
	public void visitEsecuzioneASerie(EsecuzioneASerie esecuzione) {
		info.append(esecuzione.getEsercizio().getNomeEsercizio()+" "
			+esecuzione.getSerie()+"x"+esecuzione.getRipetizioniPerSerie()+" ("+esecuzione.getPeso()+"kg)"
			+"\n");
	}

	@Override
	public void visitEsecuzioneATempo(EsecuzioneATempo esecuzione) {
		info.append(esecuzione.getEsercizio().getNomeEsercizio()+" "
			+esecuzione.getMinuti()+" minuti a "+esecuzione.getIntensita()+" intensità"
			+"\n");
	}

	public String get() {
		return info.deleteCharAt(info.length()-1).toString();
	}

}
