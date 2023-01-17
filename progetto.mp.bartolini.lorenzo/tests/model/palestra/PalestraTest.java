package model.palestra;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import controller.ControllerMock;
import model.abbonamenti.AbbonamentoMock;
import model.cliente.Cliente;
import model.cliente.ClienteInterface;
import model.esecuzioni.EsecuzioneASerie;
import model.esecuzioni.EsecuzioneEsercizioInterface;
import model.esercizi.PancaPiana;
import model.esercizi.RematoreConBilanciere;
import model.esercizi.RematoreConManubri;
import model.postazioni.PostazioneInterface;
import model.postazioni.PostazioneMultipla;
import model.postazioni.PostazioneSingola;
import model.scheda.Scheda;
import model.scheda.SchedaInterface;

public class PalestraTest {

	@Test
	public void testGetClienteFromCodice() {
		Collection<ClienteInterface> clientiInPalestra = new ArrayList<>();	
		Palestra palestra = new Palestra("test", null, null, null, clientiInPalestra);
		clientiInPalestra.add(new Cliente(palestra, null, "ABC123", null, null, null, 0, 0));
		
		assertThat(palestra.getClienteFromCodice("000000"))
			.isEmpty();
		assertThat(palestra.getClienteFromCodice("ABC123"))
			.isPresent();
	}

	@Test
	public void testGetPostazioniFromEsercizio() {
		Collection<PostazioneInterface> postazioni = new ArrayList<>();
		Collection<PostazioneInterface> postPancaPiana = new ArrayList<>();
		Collection<PostazioneInterface> postRematoreConManubri = new ArrayList<>();
		Palestra palestra = new Palestra("test", null, null, postazioni, null);
		
		postPancaPiana.add(new PostazioneSingola(PancaPiana.getInstance(), null));
		postPancaPiana.add(new PostazioneMultipla(PancaPiana.getInstance(), 3, null, null));
		postPancaPiana.add(new PostazioneSingola(PancaPiana.getInstance(), null));
		
		postRematoreConManubri.add(new PostazioneSingola(RematoreConManubri.getInstance(), null));
		
		postazioni.addAll(postPancaPiana);
		postazioni.addAll(postRematoreConManubri);
		
		assertThat(palestra.getPostazioniFromEsercizio(PancaPiana.getInstance()))
			.containsExactlyInAnyOrderElementsOf(postPancaPiana);
		
		assertThat(palestra.getPostazioniFromEsercizio(RematoreConBilanciere.getInstance()))
			.isEmpty();
		
		assertThat(palestra.getPostazioniFromEsercizio(RematoreConManubri.getInstance()))
			.containsExactlyInAnyOrderElementsOf(postRematoreConManubri);
	}
	
	@Test
	public void testPrenotaEserciziConScheda() {
		Collection<PostazioneInterface> postazioniPalestra = new ArrayList<>();
		Collection<PostazioneInterface> postazioniOk = new ArrayList<>();
		Collection<PostazioneInterface> postazioniNonOk = new ArrayList<>();
		
		postazioniOk.add(new PostazioneSingola(PancaPiana.getInstance(), new ArrayList<>()));
		postazioniOk.add(new PostazioneMultipla(PancaPiana.getInstance(), 2, new ArrayList<>(), new ArrayList<>()));
		postazioniOk.add(new PostazioneSingola(RematoreConManubri.getInstance(), new ArrayList<>()));
		
		postazioniNonOk.add(new PostazioneSingola(RematoreConBilanciere.getInstance(), new ArrayList<>()));
		postazioniNonOk.add(new PostazioneSingola(RematoreConBilanciere.getInstance(), new ArrayList<>()));
		
		postazioniPalestra.addAll(postazioniOk);
		postazioniPalestra.addAll(postazioniNonOk);
		
		Collection<ClienteInterface> clientiInPalestra = new ArrayList<>();	
		
		ControllerMock controller = new ControllerMock();
		Palestra palestra = new Palestra("test", controller, null, postazioniPalestra, clientiInPalestra);
		
		Collection<PostazioneInterface> postazioniPrenotateCliente = new ArrayList<>();
		Cliente cliente = new Cliente(palestra, new AbbonamentoMock(true), "ABC123", postazioniPrenotateCliente, null, null, 0, 0);
		clientiInPalestra.add(cliente);
		
		Collection<EsecuzioneEsercizioInterface> esecuzioni = new ArrayList<>();
		esecuzioni.add(new EsecuzioneASerie(PancaPiana.getInstance(), 0, 0, 0));
		esecuzioni.add(new EsecuzioneASerie(RematoreConManubri.getInstance(), 0, 0, 0));
		SchedaInterface scheda = new Scheda(esecuzioni);
		
		assertThat(palestra.prenotaEserciziConScheda(scheda, cliente.getCodiceCliente()))
			.isTrue();
		assertThat(controller.notifiche)
			.isEqualTo(postazioniOk.size());
		
		assertThat(postazioniPrenotateCliente)
			.containsExactlyInAnyOrderElementsOf(postazioniOk);
		assertThat(postazioniPrenotateCliente)
			.doesNotContainAnyElementsOf(postazioniNonOk);
	}
	
	@Test
	public void testPrenotaEserciziConSchedaBadPath() {
		Collection<ClienteInterface> clientiInPalestra = new ArrayList<>();	
		
		Palestra palestra = new Palestra("test", null, null, null, clientiInPalestra);
		
		Collection<PostazioneInterface> postazioniPrenotateCliente = new ArrayList<>();
		Cliente cliente = new Cliente(palestra, new AbbonamentoMock(false), "ABC123", postazioniPrenotateCliente, null, null, 0, 0);
		clientiInPalestra.add(cliente);
		
		Collection<EsecuzioneEsercizioInterface> esecuzioni = new ArrayList<>();
		esecuzioni.add(new EsecuzioneASerie(PancaPiana.getInstance(), 0, 0, 0));
		esecuzioni.add(new EsecuzioneASerie(RematoreConManubri.getInstance(), 0, 0, 0));
		SchedaInterface scheda = new Scheda(esecuzioni);
		
		assertThat(palestra.prenotaEserciziConScheda(scheda, cliente.getCodiceCliente()))
			.isFalse();
	}
	
	@Test
	public void testPrenotaEsercizio() {
		Collection<PostazioneInterface> postazioniPalestra = new ArrayList<>();
		Collection<PostazioneInterface> postazioniOk = new ArrayList<>();
		Collection<PostazioneInterface> postazioniNonOk = new ArrayList<>();
		
		postazioniOk.add(new PostazioneSingola(PancaPiana.getInstance(), new ArrayList<>()));
		postazioniOk.add(new PostazioneSingola(PancaPiana.getInstance(), new ArrayList<>()));
		
		postazioniNonOk.add(new PostazioneSingola(RematoreConBilanciere.getInstance(), new ArrayList<>()));
		
		postazioniPalestra.addAll(postazioniOk);
		postazioniPalestra.addAll(postazioniNonOk);
		
		Collection<ClienteInterface> clientiInPalestra = new ArrayList<>();	
		
		ControllerMock controller = new ControllerMock();
		Palestra palestra = new Palestra("test", controller, null, postazioniPalestra, clientiInPalestra);
		
		Collection<PostazioneInterface> postazioniPrenotateCliente = new ArrayList<>();
		Cliente cliente = new Cliente(palestra, new AbbonamentoMock(true), "ABC123", postazioniPrenotateCliente, null, null, 0, 0);
		clientiInPalestra.add(cliente);
		
		assertThat(palestra.prenotaEsercizio(PancaPiana.getInstance(), cliente.getCodiceCliente()))
			.isTrue();
		assertThat(controller.notifiche)
			.isEqualTo(postazioniOk.size());
		
		assertThat(postazioniPrenotateCliente)
			.containsExactlyInAnyOrderElementsOf(postazioniOk);
		assertThat(postazioniPrenotateCliente)
			.doesNotContainAnyElementsOf(postazioniNonOk);
	}
	
	@Test
	public void testPrenotaEsercizioBadPath() {
		Collection<PostazioneInterface> postazioniPalestra = new ArrayList<>();
		Collection<PostazioneInterface> postazioniOk = new ArrayList<>();
		Collection<PostazioneInterface> postazioniNonOk = new ArrayList<>();
		
		postazioniOk.add(new PostazioneMultipla(PancaPiana.getInstance(), 2, new ArrayList<>(), new ArrayList<>()));
		postazioniOk.add(new PostazioneSingola(PancaPiana.getInstance(), new ArrayList<>()));
		
		postazioniNonOk.add(new PostazioneSingola(RematoreConBilanciere.getInstance(), new ArrayList<>()));
		
		postazioniPalestra.addAll(postazioniOk);
		postazioniPalestra.addAll(postazioniNonOk);
		
		Collection<ClienteInterface> clientiInPalestra = new ArrayList<>();	
		
		Palestra palestra = new Palestra("test", null, null, postazioniPalestra, clientiInPalestra);
		
		Collection<PostazioneInterface> postazioniPrenotateCliente = new ArrayList<>();
		Cliente cliente = new Cliente(palestra, new AbbonamentoMock(false), "ABC123", postazioniPrenotateCliente, null, null, 0, 0);
		clientiInPalestra.add(cliente);
		
		assertThat(palestra.prenotaEsercizio(PancaPiana.getInstance(), cliente.getCodiceCliente()))
			.isFalse();
	}
	
}
