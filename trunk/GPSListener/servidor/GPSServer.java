package servidor;

import gps.GPSHandler;

import java.net.*;

import java.io.*;
import java.util.*;

import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.UnsupportedCommOperationException;
import javax.swing.JOptionPane;

import log.MyLogger;

import sms.SerialToGsm;

public class GPSServer extends Thread {
	public static Vector<String> x = new Vector<String>();
	public static Vector<GPSHandler> gpsh = new Vector<GPSHandler>();
	public static FramePrincipal wp;

	// Conexion con el modem GSM
	public static SerialToGsm stg;

	private int hostPort;

	public GPSServer(int hostPort, String GsmModemPort) {
		this.hostPort = hostPort;
		// Gsm Modem
		try {
			stg = new SerialToGsm(GsmModemPort);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error de entrada/salida en el dispositivo GSM Modem.",
					e.toString(), JOptionPane.ERROR_MESSAGE);
			MyLogger.escribirLog(this.getClass().getName(), e.toString());
			e.printStackTrace();
		} catch (NoSuchPortException e) {
			JOptionPane.showMessageDialog(null,
					"El dispositivo GSM Modem no ha sido hayado.",
					e.toString(), JOptionPane.ERROR_MESSAGE);
			MyLogger.escribirLog(this.getClass().getName(), e.toString());
			e.printStackTrace();
		} catch (PortInUseException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"El dispositivo GSM Modem esta siendo usado por otra aplicacion.",
							e.toString(), JOptionPane.ERROR_MESSAGE);
			MyLogger.escribirLog(this.getClass().getName(), e.toString());
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Se ha solicitado una operacion no soportada por el puerto serial.",
							e.toString(), JOptionPane.ERROR_MESSAGE);
			MyLogger.escribirLog(this.getClass().getName(), e.toString());
			e.printStackTrace();
		}

		// Ventana Principal
		wp = new FramePrincipal();

		start();
	}

	@Override
	public void run() {
		try {
			// Host
			ServerSocket server;
			server = new ServerSocket(hostPort);
			while (true) {
				Socket client = server.accept();
				GPSHandler gps = new GPSHandler(client);
				addConectados(client.getInetAddress().toString(), gps);
				gps.start();
				MyLogger.escribirLog(this.getClass().getName(), "Entro "
						+ client.getInetAddress());
				GPSServer.wp.lblStatus.setText("Entro "
						+ client.getInetAddress());
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error de entrada/salida escuchando puerto de internet.",
					e.toString(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			MyLogger.escribirLog(this.getClass().getName(), e.toString());
		}
	}

	public static void removeConectados(int index) {
		x.remove(index);
		GPSServer.gpsh.remove(index);
		GPSServer.wp.lstConectados.setListData(x);
		GPSServer.wp.lblConectados.setText("Conectados (" + x.size() + ")");
	}

	public static void removeConectados(String ia, GPSHandler gpsh) {
		x.remove(ia);
		GPSServer.gpsh.remove(gpsh);
		GPSServer.wp.lstConectados.setListData(x);
		GPSServer.wp.lblConectados.setText("Conectados (" + x.size() + ")");
	}

	public static void addConectados(String ia, GPSHandler gpsh) {
		x.add(ia);
		GPSServer.gpsh.add(gpsh);
		GPSServer.wp.lstConectados.setListData(x);
		GPSServer.wp.lblConectados.setText("Conectados (" + x.size() + ")");
	}

	public static String addApodo(String apodo, String ia) {
		int i = x.indexOf(ia);
		x.set(i, apodo + " - " + ia);
		GPSServer.wp.lstConectados.setListData(x);
		return x.get(i);
	}
}