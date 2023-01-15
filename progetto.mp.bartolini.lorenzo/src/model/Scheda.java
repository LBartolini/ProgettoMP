package model;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Scheda implements SchedaInterface {

	private Collection<EsecuzioneEsercizioInterface> esecuzioni;
	
	public Scheda(Collection<EsecuzioneEsercizioInterface> esecuzioni) {
		this.esecuzioni = esecuzioni;
	}
	
	public void addEsecuzione(EsecuzioneEsercizioInterface e) {
		esecuzioni.add(e);
	}
	
	public Scheda with(EsecuzioneEsercizioInterface e) {
		esecuzioni.add(e);
		return this;
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
		// TODO VISITOR
		return 0;
	}
	
	@Override
	public int calcolaDifficoltà() {
		// TODO visitor con esecuzione
		return 0;
	}

}
