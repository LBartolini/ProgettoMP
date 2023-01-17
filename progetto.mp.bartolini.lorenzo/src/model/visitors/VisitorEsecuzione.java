package model.visitors;

import model.esecuzioni.EsecuzioneASerie;
import model.esecuzioni.EsecuzioneATempo;

public interface VisitorEsecuzione {

	public void visitEsecuzioneASerie(EsecuzioneASerie esecuzione);
	
	public void visitEsecuzioneATempo(EsecuzioneATempo esecuzione);
	
}
