package model.abbonamenti;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import model.esercizi.PancaPiana;
import model.esercizi.RematoreConBilanciere;
import model.esercizi.RematoreConManubri;

public class AbbonamentoTest {

	@Test
	public void testEsercizioPermesso() {
		Abbonamento abbonamento = new PacchettoSchiena(
					new AbbonamentoVuoto()
				);
		
		assertThat(abbonamento.isEsercizioPermesso(RematoreConBilanciere.getInstance()))
			.isTrue();
		
		assertThat(abbonamento.isEsercizioPermesso(RematoreConManubri.getInstance()))
			.isTrue();
		
		assertThat(abbonamento.isEsercizioPermesso(PancaPiana.getInstance()))
			.isFalse();
		
		abbonamento = new PacchettoPetto(abbonamento);
		
		assertThat(abbonamento.isEsercizioPermesso(RematoreConBilanciere.getInstance()))
		.isTrue();
	
		assertThat(abbonamento.isEsercizioPermesso(RematoreConManubri.getInstance()))
			.isTrue();
	
		assertThat(abbonamento.isEsercizioPermesso(PancaPiana.getInstance()))
			.isTrue();
	
		
	}
	
	@Test
	public void testGetEserciziPermessi() {
		Abbonamento abbonamento = new PacchettoSchiena(
				new AbbonamentoVuoto()
			);
		assertThat(abbonamento.getEserciziPermessi())
			.containsExactlyInAnyOrder(RematoreConBilanciere.getInstance(),
					RematoreConManubri.getInstance());
		
		abbonamento = new PacchettoPetto(abbonamento);
		assertThat(abbonamento.getEserciziPermessi())
			.containsExactlyInAnyOrder(RematoreConBilanciere.getInstance(),
					RematoreConManubri.getInstance(),
					PancaPiana.getInstance());
	}
	
	@Test
	public void testPrezzo() {
		Abbonamento abbonamento = new PacchettoSchiena(
					new AbbonamentoVuoto()
				);
		assertThat(abbonamento.getPrezzo())
			.isEqualTo(PacchettoSchiena.PREZZO);
		
		abbonamento = new PacchettoPetto(abbonamento);
		assertThat(abbonamento.getPrezzo())
			.isEqualTo(PacchettoSchiena.PREZZO+PacchettoPetto.PREZZO);
		
	}

}
