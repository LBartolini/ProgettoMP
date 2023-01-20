package model;

import java.util.Collection;
import java.util.stream.Collectors;

import model.esecuzioni.Esecuzione;
import model.esercizi.Esercizio;
import model.visitors.CostoVisitor;
import model.visitors.InfoVisitor;

public final class Scheda {

	private Collection<Esecuzione> esecuzioni;
	
	public Scheda(Collection<Esecuzione> esecuzioni) {
		this.esecuzioni = esecuzioni;
	}
	
	public Collection<Esercizio> getEsercizi() {
		return esecuzioni.stream()
				.map(esecuzione -> esecuzione.getEsercizio())
				.distinct()
				.collect(Collectors.toList());
	}

	public Collection<Esecuzione> getEsecuzioniFromEsercizio(Esercizio esercizio) {
		return esecuzioni.stream()
				.filter(esecuzione -> esecuzione.getEsercizio().equals(esercizio))
				.collect(Collectors.toList());
	}

	public double calcolaCosto() {
		CostoVisitor visitor = new CostoVisitor();
		
		esecuzioni.forEach(esecuzione -> esecuzione.accept(visitor));
		
		return visitor.get();
	}
	
	public String getInfo() {
		InfoVisitor visitor = new InfoVisitor();
		
		esecuzioni.forEach(esecuzione -> esecuzione.accept(visitor));
		
		return visitor.get();
	}

}
