package model.abbonamenti;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.esercizi.Esercizio;

public abstract class AbbonamentoDecorator implements Abbonamento {

	private Abbonamento abbonamento;
	private double prezzo;
	
	protected AbbonamentoDecorator(Abbonamento abbonamento, double prezzo) {
		Objects.requireNonNull(abbonamento, "L'oggetto decorato non può essere null");
		this.abbonamento = abbonamento;
		this.prezzo = prezzo;
	}

	@Override
	public final double getPrezzo() {
		return prezzo + abbonamento.getPrezzo();
	}
	
	@Override
	public final Collection<Esercizio> getEserciziPermessi(){
		return Stream.concat(
				abbonamento.getEserciziPermessi().stream(), 
				getEserciziLocali().stream())
			.collect(Collectors.toList());
	}
	
	protected abstract Collection<Esercizio> getEserciziLocali();
	
	@Override
	public final boolean isEsercizioPermesso(Esercizio esercizio) {
		if(getEserciziLocali().contains(esercizio))
			return true;
		
		return abbonamento.isEsercizioPermesso(esercizio);
	}

}
