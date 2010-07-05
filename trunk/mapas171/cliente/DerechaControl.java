package org.mapas171.cliente;
import java.awt.event.ActionEvent;

public class DerechaControl extends ListenerDeControl {

	public DerechaControl(Mapa mapa) {
		super(mapa);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Thread() {
			@Override
			public void run() {
				DerechaControl.this.mapa.derecha();
			}
		}.start();
	}

}
