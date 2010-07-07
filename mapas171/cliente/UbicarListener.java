package org.mapas171.cliente;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JList;

public class UbicarListener extends ListenerDeControl {
	JList lstGPS;
	String id;
	JButton btn;

	public UbicarListener(Mapa creador) {
		super(creador);
		lstGPS = null;
		id = null;
	}

	public UbicarListener(Mapa creador, JList lstGPS) {
		super(creador);
		this.lstGPS = lstGPS;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		btn = (JButton) arg0.getSource();
		id = (String) lstGPS.getModel().getElementAt(lstGPS.getSelectedIndex());
		new Thread() {
			@Override
			public void run() {
				btn.setEnabled(false);
				try {
					BDAcess bd = new BDAcess();
					ResultSet res = bd
							.consultar("SELECT * FROM Localizacion WHERE codUnidad = '"
									+ id + "'");
					Double lat = null;
					Double lon = null;
					while (res.next()) {
						lat = Double.parseDouble(res.getString(2));
						lon = Double.parseDouble(res.getString(3));
					}
					UbicarListener.this.mapa.dibujar(lat, lon, true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				btn.setEnabled(true);
			}
		}.start();
	}
}
