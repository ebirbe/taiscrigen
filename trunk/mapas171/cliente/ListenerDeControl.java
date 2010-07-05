package org.mapas171.cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ListenerDeControl implements ActionListener {

	Mapa mapa;

	public ListenerDeControl(Mapa mapa) {
		this.mapa = mapa;
	}

	@Override
	public abstract void actionPerformed(ActionEvent arg0);

}
