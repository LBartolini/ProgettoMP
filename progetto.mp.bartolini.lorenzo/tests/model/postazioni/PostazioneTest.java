package model.postazioni;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.ControllerMock;
import model.Cliente;
import model.Palestra;
import model.esercizi.PancaPiana;

public class PostazioneTest {

	@Test
	public void testOccupazioneSingola() {
		PostazioneSingola postazione = new PostazioneSingola(PancaPiana.getInstance(), new ArrayList<>());
		PostazioneSingola postazione2 = new PostazioneSingola(PancaPiana.getInstance(), new ArrayList<>());
		Palestra p = new Palestra(null, new ControllerMock(), null, null, null);

		Cliente cliente = new Cliente(p, null, "A", new ArrayList<>(), null, null);
		
		cliente.prenotaPostazione(postazione);
		cliente.prenotaPostazione(postazione2);
		assertThat(cliente.occupaPostazione(postazione.getCodicePostazione()))
			.isTrue();
		
		assertThat(postazione.clienteAttuale.orElse(null))
			.isEqualTo(cliente);
		assertThat(postazione.getPostiDisponibili())
			.isEqualTo(0);
		assertThat(postazione.clientiInAttesa)
			.isEmpty();
		assertThat(postazione2.clientiInAttesa)
			.isEmpty();
	}
	
	@Test
	public void testOccupazioneSingolaGiàOccupata() {
		PostazioneSingola postazione = new PostazioneSingola(PancaPiana.getInstance(), new ArrayList<>());
		Palestra p = new Palestra(null, new ControllerMock(), null, null, null);
		
		Cliente cliente1 = new Cliente(p, null, "A", new ArrayList<>(), null, null);
		Cliente cliente2 = new Cliente(p, null, "B", new ArrayList<>(), null, null);
		
		cliente1.prenotaPostazione(postazione);
		cliente2.prenotaPostazione(postazione);
		
		assertThat(cliente1.occupaPostazione(postazione.getCodicePostazione()))
			.isTrue();
		assertThat(postazione.getPostiDisponibili())
			.isEqualTo(0);
		
		assertThat(cliente2.occupaPostazione(postazione.getCodicePostazione()))
			.isFalse();
		assertThat(postazione.clienteAttuale.orElse(null))
			.isEqualTo(cliente1);
	}
	
	@Test
	public void testOccupazioneMultipla() {
		PostazioneMultipla postazione = new PostazioneMultipla(PancaPiana.getInstance(), 2, 
				new ArrayList<>(), new ArrayList<>());
		PostazioneMultipla postazione2 = new PostazioneMultipla(PancaPiana.getInstance(), 2, 
				new ArrayList<>(), new ArrayList<>());
		Palestra p = new Palestra(null, new ControllerMock(), null, null, null);

		Cliente cliente = new Cliente(p, null, "A", new ArrayList<>(), null, null);
		Cliente cliente2 = new Cliente(p, null, "B", new ArrayList<>(), null, null);
		
		cliente.prenotaPostazione(postazione);
		cliente.prenotaPostazione(postazione2);
		cliente2.prenotaPostazione(postazione);
		cliente2.prenotaPostazione(postazione2);
		
		assertThat(postazione.clientiInAttesa)
			.containsExactlyInAnyOrder(cliente, cliente2);
		assertThat(postazione2.clientiInAttesa)
			.containsExactlyInAnyOrder(cliente, cliente2);
		
		assertThat(cliente.occupaPostazione(postazione.getCodicePostazione()))
			.isTrue();
		assertThat(postazione.clientiAttuali)
			.contains(cliente);
		assertThat(postazione.getPostiDisponibili())
			.isEqualTo(1);
		assertThat(postazione.clientiInAttesa)
			.containsExactlyInAnyOrder(cliente2);
		
		assertThat(cliente2.occupaPostazione(postazione.getCodicePostazione()))
			.isTrue();
		assertThat(postazione.clientiAttuali)
			.containsExactly(cliente, cliente2);
		assertThat(postazione.getPostiDisponibili())
			.isEqualTo(0);
		
		assertThat(postazione.clientiInAttesa)
			.isEmpty();
		assertThat(postazione2.clientiInAttesa)
			.isEmpty();
	}
	
	@Test
	public void testOccupazioneMultiplaGiàOccupata() {
		PostazioneMultipla postazione = new PostazioneMultipla(PancaPiana.getInstance(), 1, 
				new ArrayList<>(), new ArrayList<>());
		Palestra p = new Palestra(null, new ControllerMock(), null, null, null);

		Cliente cliente = new Cliente(p, null, "A", new ArrayList<>(), null, null);
		Cliente cliente2 = new Cliente(p, null, "B", new ArrayList<>(), null, null);
		
		cliente.prenotaPostazione(postazione);
		cliente2.prenotaPostazione(postazione);
		
		assertThat(postazione.clientiInAttesa)
			.containsExactlyInAnyOrder(cliente, cliente2);
		
		assertThat(cliente.occupaPostazione(postazione.getCodicePostazione()))
			.isTrue();
		assertThat(postazione.getPostiDisponibili())
			.isEqualTo(0);
		assertThat(postazione.clientiInAttesa)
			.containsExactlyInAnyOrder(cliente2);
		
		assertThat(cliente2.occupaPostazione(postazione.getCodicePostazione()))
			.isFalse();
		assertThat(postazione.clientiAttuali)
			.containsExactly(cliente);
		assertThat(postazione.clientiInAttesa)
			.containsExactlyInAnyOrder(cliente2);
	}
	
	@Test
	public void testRilascioSingola() {
		PostazioneSingola postazione = new PostazioneSingola(PancaPiana.getInstance(), new ArrayList<>());
		ControllerMock controller = new ControllerMock();
		Palestra p = new Palestra(null, controller, null, null, null);

		Cliente cliente = new Cliente(p, null, "A", new ArrayList<>(), null, null);
		Cliente cliente2 = new Cliente(p, null, "B", new ArrayList<>(), null, null);
		Cliente cliente3 = new Cliente(p, null, "C", new ArrayList<>(), null, null);
		
		cliente.prenotaPostazione(postazione);
		cliente2.prenotaPostazione(postazione);
		cliente3.prenotaPostazione(postazione);
		
		cliente.occupaPostazione(postazione.getCodicePostazione());
		cliente.rilasciaPostazione(postazione.getCodicePostazione());
		
		assertThat(postazione.clienteAttuale.orElse(null))
			.isNull();
		assertThat(controller.notifiche)
			.isEqualTo(5);
	}
	
	@Test
	public void testRilascioMultipla() {
		PostazioneMultipla postazione = new PostazioneMultipla(PancaPiana.getInstance(), 2, new ArrayList<>(), new ArrayList<>());
		ControllerMock controller = new ControllerMock();
		Palestra p = new Palestra(null, controller, null, null, null);

		Cliente cliente = new Cliente(p, null, "A", new ArrayList<>(), null, null);
		Cliente cliente2 = new Cliente(p, null, "B", new ArrayList<>(), null, null);
		Cliente cliente3 = new Cliente(p, null, "C", new ArrayList<>(), null, null);
		
		cliente.prenotaPostazione(postazione);
		cliente2.prenotaPostazione(postazione);
		cliente3.prenotaPostazione(postazione);
		
		cliente.occupaPostazione(postazione.getCodicePostazione());
		cliente2.occupaPostazione(postazione.getCodicePostazione());
		
		cliente.rilasciaPostazione(postazione.getCodicePostazione());
		
		assertThat(postazione.clientiAttuali)
			.containsExactly(cliente2);
		assertThat(controller.notifiche)
			.isEqualTo(4);
	}

}
