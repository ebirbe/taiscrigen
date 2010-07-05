package org.mapas171.cliente;
import java.awt.event.ActionEvent;

public class AbajoControl extends ListenerDeControl {

	public AbajoControl(Mapa mapa) {
		super(mapa);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Thread() {
			@Override
			public void run() {
				AbajoControl.this.mapa.bajar();
			}
		}.start();
	}

}
