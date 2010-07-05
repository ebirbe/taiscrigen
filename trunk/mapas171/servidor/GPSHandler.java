package org.mapas171.servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.mapas171.cliente.BDAcess;
import org.mapas171.servidor.interprete.Comando;
import org.mapas171.servidor.interprete.ExcepcionComandoInvalido;
import org.mapas171.servidor.interprete.SelectorDeComandos;

public class GPSHandler extends Thread {
	protected Socket s;
	protected DataInputStream i;
	protected DataOutputStream o;
	public String id = "";
	public String name;
	private String msg;
	public boolean leer = true;

	public GPSHandler(Socket s) throws IOException {
		this.s = s;
		i = new DataInputStream(new BufferedInputStream(s.getInputStream()));
		o = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
		// i = new DataInputStream(new BufferedInputStream(System.in));
		// o = new DataOutputStream(new BufferedOutputStream(System.out));
	}

	@Override
	public void run() {
		name = s.getInetAddress().toString();
		try {
			enviarMensaje(">QCP<");
			cicloDeLectura(leer);
		} catch (IOException ex) {
			System.out.println(name + " ya no esta a la vista.");
		} finally {
			try {
				finalize();
			} catch (Throwable e) {
			}
		}
	}

	private void cicloDeLectura(boolean leer) throws IOException {
		while (leer) {
			msg = leerMensaje();
			if (id.equals("") && msg.indexOf(";ID=") != -1) {
				id = msg.substring(msg.indexOf(";ID=") + 4, msg.indexOf("<"));
				name = id;
			}
			ecribirEnPantalla(msg);
		}
	}

	private void ecribirEnPantalla(String msg2) {
		String sql = "";
		java.util.Date hoy = new java.util.Date();
		Date hoySQL = new Date(hoy.getTime());
		// Comando en formato RAW
		System.out.println(msg2);
		Comando c = null;
		try {
			SelectorDeComandos sel;
			sel = new SelectorDeComandos(msg2);
			c = sel.seleccionarComando();
			if (c != null) {
				if (c.isLocalizable()) {
					c.desglosarComando();
					Vector<String> nombres = getPersonasEnUnidad(id);
					BDAcess bd = new BDAcess();
					sql = "INSERT INTO Localizacion VALUES ('" + id + "', '"
							+ c.getLatitud() + "', '" + c.getLongitud()
							+ "', #" + hoySQL + "#, '" + nombres.get(0) + "','"
							+ nombres.get(1) + "', '" + nombres.get(2) + "', '"
							+ nombres.get(3) + "', 'ADMIN')";
					System.out.println(sql);
					bd.modificar(sql);
					bd.desconectar();
				}
			}
		} catch (ExcepcionComandoInvalido e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Vector<String> getPersonasEnUnidad(String id2) throws SQLException {
		String sql = "";
		Vector<String> nombres = new Vector<String>();
		BDAcess bd = new BDAcess();
		sql = "SELECT MAX(Fecha_asignado) FROM Pers_asig_uni WHERE codUnidad='"
				+ id + "'";
		System.out.println(sql);
		ResultSet res = bd.consultar(sql);
		Date fecha = null;
		while (res.next()) {
			fecha = res.getDate(1);
		}
		bd.desconectar();
		bd.conectar();
		sql = "SELECT Nombres, Apellidos FROM Pers_asig_uni WHERE codUnidad='"
				+ id + "' AND Fecha_asignado=#" + fecha.toString() + "#";
		System.out.println(sql);
		res = bd.consultar(sql);
		int i = 0;
		while (res.next()) {
			if (i++ == 3)
				break;
			System.out.println(res.toString());
			nombres.add(res.getString(2) + ", " + res.getString(1));
		}
		for (i -= 1; i < 3; i++) {
			nombres.add("");
		}
		bd.desconectar();
		return nombres;
	}

	public String leerMensaje() throws IOException {
		String msg = new String();
		while (true) {
			Character c = (char) i.read();
			Integer inte = (int) c;
			if (inte < 0 || inte > 127)
				throw new IOException(name + " envio peticion de salida.");

			msg += c.toString();
			if (i.available() == 0 && !c.toString().equals("\n")) {
				msg += "\n";
			}
			if (i.available() == 0)
				break;
		}
		return msg;
	}

	public boolean enviarMensaje(String msg) {
		boolean exito = true;
		try {
			for (int j = 0; j < msg.length(); j++) {
				o.write((int) msg.charAt(j));
			}
			o.flush();
			System.out.println("[ENVIO]:" + msg);
		} catch (IOException ex) {
			ex.printStackTrace();
			exito = false;
		}
		return exito;
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			leer = false;
			s.close();
			o.close();
			i.close();
		} catch (Exception e) {
		}
		super.finalize();
	}
}
