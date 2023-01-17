package model.scheda;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import model.esecuzioni.EsecuzioneEsercizioInterface;
import model.esercizi.EsercizioInterface;
import model.visitors.CostoVisitor;
import model.visitors.DifficoltàVisitor;

public final class Scheda implements SchedaInterface {

	private Collection<EsecuzioneEsercizioInterface> esecuzioni;
	
	public Scheda(Collection<EsecuzioneEsercizioInterface> esecuzioni) {
		this.esecuzioni = esecuzioni;
	}
	
	public void addEsecuzione(EsecuzioneEsercizioInterface e) {
		esecuzioni.add(e);
	}
	
	@Override
	public Collection<EsercizioInterface> getEsercizi() {
		return esecuzioni.stream()
				.map(esecuzione -> esecuzione.getEsercizio())
				.distinct()
				.collect(Collectors.toList());
	}

	@Override
	public Optional<EsecuzioneEsercizioInterface> getEsecuzioneEsercizio(EsercizioInterface esercizio) {
		return esecuzioni.stream()
				.filter(esecuzione -> esecuzione.getEsercizio().equals(esercizio))
				.findAny();
	}

	@Override
	public double calcolaCosto() {
		CostoVisitor visitor = new CostoVisitor();
		
		esecuzioni.forEach(esecuzione -> esecuzione.accept(visitor));
		
		return visitor.get();
	}
	
	@Override
	public double calcolaDifficoltà(double pesoCliente) {
		DifficoltàVisitor visitor = new DifficoltàVisitor(pesoCliente);
		
		esecuzioni.forEach(esecuzione -> esecuzione.accept(visitor));
		
		return visitor.get();
	}

}
