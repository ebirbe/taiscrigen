package org.mapas171.cliente;
import java.awt.event.ActionEvent;

public class ZoomMasControl extends ListenerDeControl {

	public ZoomMasControl(Mapa mapa) {
		super(mapa);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Thread() {
			@Override
			public void run() {
				ZoomMasControl.this.mapa.acercar();
			}
		}.start();
	}

}
