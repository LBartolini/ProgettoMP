package model.scheda;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import model.esecuzioni.EsecuzioneASerie;
import model.esecuzioni.EsecuzioneATempo;
import model.esecuzioni.EsecuzioneEsercizioInterface;
import model.esercizi.PancaPiana;
import model.esercizi.RematoreConBilanciere;
import model.esercizi.RematoreConManubri;

public class SchedaTest {

	@Test
	public void testGetEsercizi() {
		Collection<EsecuzioneEsercizioInterface> esecuzioni = new ArrayList<>();
		esecuzioni.add(new EsecuzioneASerie(PancaPiana.getInstance(), 0, 0, 0));
		esecuzioni.add(new EsecuzioneASerie(RematoreConManubri.getInstance(), 0, 0, 0));
		
		Scheda scheda = new Scheda(esecuzioni);
		
		assertThat(scheda.getEsercizi())
			.containsExactlyInAnyOrder(PancaPiana.getInstance(), RematoreConManubri.getInstance());
		
		esecuzioni.add(new EsecuzioneATempo(PancaPiana.getInstance(), 0, null));
		
		assertThat(scheda.getEsercizi())
			.containsExactlyInAnyOrder(PancaPiana.getInstance(), RematoreConManubri.getInstance());
	}
	
	@Test
	public void testGetEsecuzioniEsercizio() {
		Collection<EsecuzioneEsercizioInterface> esecuzioni = new ArrayList<>();
		EsecuzioneEsercizioInterface esecuzione1 = new EsecuzioneASerie(PancaPiana.getInstance(), 0, 0, 0);
		EsecuzioneEsercizioInterface esecuzione2 = new EsecuzioneASerie(PancaPiana.getInstance(), 0, 0, 0);
		EsecuzioneEsercizioInterface esecuzione3 = new EsecuzioneATempo(RematoreConManubri.getInstance(), 0, null);
		esecuzioni.add(esecuzione1);
		esecuzioni.add(esecuzione2);
		esecuzioni.add(esecuzione3);
		
		Scheda scheda = new Scheda(esecuzioni);
		
		assertThat(scheda.getEsecuzioniFromEsercizio(PancaPiana.getInstance()))
			.containsExactlyInAnyOrder(esecuzione1, esecuzione2);
		
		assertThat(scheda.getEsecuzioniFromEsercizio(RematoreConManubri.getInstance()))
			.containsExactlyInAnyOrder(esecuzione3);
	}
	
	@Test
	public void testCalcoloCosto() {
		Collection<EsecuzioneEsercizioInterface> esecuzioni = new ArrayList<>();
		esecuzioni.add(new EsecuzioneASerie(PancaPiana.getInstance(), 4, 5, 70));
		esecuzioni.add(new EsecuzioneATempo(RematoreConManubri.getInstance(), 20, null));
		
		Scheda scheda = new Scheda(esecuzioni);
		assertThat(scheda.calcolaCosto())
			.isEqualTo(30); // 10 costo primo esercizio, 20 costo secondo esercizio
	}
	
	@Test
	public void testGetInfoScheda() {
		Collection<EsecuzioneEsercizioInterface> esecuzioni = new ArrayList<>();
		esecuzioni.add(new EsecuzioneASerie(PancaPiana.getInstance(), 4, 5, 50));
		esecuzioni.add(new EsecuzioneASerie(RematoreConBilanciere.getInstance(), 4, 10, 30));
		esecuzioni.add(new EsecuzioneATempo(RematoreConManubri.getInstance(), 20, "Media"));
		
		Scheda scheda = new Scheda(esecuzioni);
		assertThat(scheda.getInfo())
			.isEqualTo("PancaPiana 4x5 (50.0kg)\n"
					+ "RematoreConBilanciere 4x10 (30.0kg)\n"
					+ "RematoreConManubri 20 minuti a Media intensità"); 
	}

}
