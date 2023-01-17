package model.abbonamenti;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.esercizi.EsercizioInterface;

public abstract class AbbonamentoDecorator implements Abbonamento {

	private Abbonamento abbonamento;
	private double prezzo;
	
	protected AbbonamentoDecorator(Abbonamento abbonamento, double prezzo) {
		this.abbonamento = abbonamento;
		this.prezzo = prezzo;
	}

	@Override
	public final double getPrezzo() {
		return prezzo + abbonamento.getPrezzo();
	}
	
	@Override
	public final Collection<EsercizioInterface> getEserciziPermessi(){
		return Stream.concat(
				abbonamento.getEserciziPermessi().stream(), 
				getEserciziLocali().stream())
			.collect(Collectors.toList());
	}
	
	protected abstract Collection<EsercizioInterface> getEserciziLocali();
	
	@Override
	public final boolean isEsercizioPermesso(EsercizioInterface esercizio) {
		if(getEserciziPermessi().contains(esercizio))
			return true;
		
		return abbonamento.isEsercizioPermesso(esercizio);
	}

}
