package org.mapas171.cliente;
import java.awt.event.ActionEvent;

public class ZoomMenosControl extends ListenerDeControl {

	public ZoomMenosControl(Mapa mapa) {
		super(mapa);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Thread() {
			@Override
			public void run() {
				ZoomMenosControl.this.mapa.alejar();
			}
		}.start();
	}

}
