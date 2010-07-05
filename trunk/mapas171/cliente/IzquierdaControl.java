package org.mapas171.cliente;
import java.awt.event.ActionEvent;

public class IzquierdaControl extends ListenerDeControl {

	public IzquierdaControl(Mapa mapa) {
		super(mapa);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Thread() {
			@Override
			public void run() {
				IzquierdaControl.this.mapa.izquierda();
			}
		}.start();
	}

}
