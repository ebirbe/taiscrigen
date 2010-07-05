package org.mapas171.cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.jposition.BasicMap;
import org.jposition.CharacterInvalidException;
import org.jposition.Colors;
import org.jposition.Coordinate;
import org.jposition.CoordinateRangeException;
import org.jposition.Dimension;
import org.jposition.DimensionRangeException;
import org.jposition.MapTypeException;
import org.jposition.Marker;
import org.jposition.MarkersMap;
import org.jposition.NoExistColorException;
import org.jposition.SizeRangeException;
import org.jposition.ZoomRangeException;

public class Mapa extends Thread {
	JLabel imagen;
	JLabel status;
	PanelControl pnc;
	Coordinate coor;
	URL icono;

	private final double ESPACIADO = 1000;
	private int zoom = 15;

	public Mapa(Ubicacion ventana) {
		icono = this.getClass().getClassLoader().getResource("wait.gif");
		try {
			coor = new Coordinate("Maracay", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagen = ventana.imagen;
		status = ventana.status;
		pnc = ventana.pnControl;
	}

	@Override
	public void run() {
		dibujar(null, null);
	}

	public void subir() {
		try {
			// System.out.println(zoom/ESPACIADO);
			coor.setLatitude(coor.getLatitude() + (zoom / ESPACIADO));
			dibujar(coor.getLatitude(), coor.getLongitude());
		} catch (CoordinateRangeException e) {
			e.printStackTrace();
		}
	}

	public void bajar() {
		try {
			// System.out.println(zoom/ESPACIADO);
			coor.setLatitude(coor.getLatitude() - (zoom / ESPACIADO));
			dibujar(coor.getLatitude(), coor.getLongitude());
		} catch (CoordinateRangeException e) {
			e.printStackTrace();
		}
	}

	public void derecha() {
		try {
			// System.out.println(zoom/ESPACIADO);
			coor.setLongitude(coor.getLongitude() + (zoom / ESPACIADO));
			dibujar(coor.getLatitude(), coor.getLongitude());
		} catch (CoordinateRangeException e) {
			e.printStackTrace();
		}
	}

	public void izquierda() {
		try {
			// System.out.println(zoom/ESPACIADO);
			coor.setLongitude(coor.getLongitude() - (zoom / ESPACIADO));
			dibujar(coor.getLatitude(), coor.getLongitude());
		} catch (CoordinateRangeException e) {
			e.printStackTrace();
		}
	}

	public void acercar() {
		zoom += 1;
		dibujar(null, null);
	}

	public void alejar() {
		zoom -= 1;
		dibujar(null, null);
	}

	public void dibujar(Double lat, Double lon) {
		boolean exito = false;
		status.setIcon(new ImageIcon(icono));
		status.setText("Cargando Mapa...");
		pnc.setEnabled(false);
		try {
			if (lat != null || lat != null)
				coor = new Coordinate(lat, lon);
			System.out.println(coor.getLatitude() + " \t "
					+ coor.getLongitude());
			Marker punto = new Marker(Colors.ColorBlue, coor, Colors.SizeMid,
					'p');
			ArrayList<Marker> listaPuntos = new ArrayList<Marker>();
			listaPuntos.add(punto);
			MarkersMap mapa = new MarkersMap("", coor, zoom, listaPuntos);
			mapa.setDimmension(new Dimension(640, 640));
			mapa.setMapType(BasicMap.RoadMap);
			imagen.setIcon(mapa.getMapImage());
			exito = true;
		} catch (IOException e) {
			status.setText("Imposible cargar el mapa.");
			e.printStackTrace();
		} catch (NoExistColorException e) {
			e.printStackTrace();
		} catch (CharacterInvalidException e) {
			e.printStackTrace();
		} catch (SizeRangeException e) {
			e.printStackTrace();
		} catch (ZoomRangeException e) {
			status.setText("Zoom limite.");
			e.printStackTrace();
		} catch (DimensionRangeException e) {
			e.printStackTrace();
		} catch (MapTypeException e) {
			e.printStackTrace();
		} catch (CoordinateRangeException e) {
			status.setText("Coordenada limite.");
			e.printStackTrace();
		}
		if (exito)
			status.setText("Listo.");
		status.setIcon(null);
		pnc.setEnabled(true);
	}
}
