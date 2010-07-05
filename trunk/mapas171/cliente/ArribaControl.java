package org.mapas171.cliente;
import java.awt.event.ActionEvent;

public class ArribaControl extends ListenerDeControl {
	public ArribaControl(Mapa mapa) {
		super(mapa);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Thread() {
			@Override
			public void run() {
				ArribaControl.this.mapa.subir();
			}
		}.start();
	}

}
