package model;

public class Cliente {
	
	private String codiceCliente;
	private Palestra palestra;

	public Cliente(Palestra palestra, String codiceCliente) {
		this.palestra = palestra;
		this.codiceCliente = codiceCliente;
	}

	public void notificaPostazioneLibera(String codicePostazione) {
		palestra.notifyClientePostazioneLibera(codicePostazione, getCodiceCliente());
	}
	
	public String getCodiceCliente() {
		return codiceCliente;
	}
	
}
