package controller;

public class ControllerMock implements Controller {
	
	public int notifiche ;

	public ControllerMock() {
		notifiche = 0;
	}

	@Override
	public void notifyClientePostazioneLibera(String codicePostazione, String codiceCliente) {
		notifiche++;
	}

}
