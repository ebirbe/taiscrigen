package servidor;
import gps.GPSHandler;

import java.net.*;

import java.io.*;
import java.util.*;

import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.UnsupportedCommOperationException;
import javax.swing.JOptionPane;

import sms.SerialToGsm;

public class  GPSServer  {
	public static Vector<String> x = new Vector<String>();
	public static Vector<GPSHandler> gpsh = new Vector<GPSHandler>();
	public static FramePrincipal wp;

	// Conexion con el modem GSM
	public static SerialToGsm stg;

	public GPSServer (int hostPort, String GsmModemPort) {
		// Gsm Modem
		try {
			stg = new SerialToGsm(GsmModemPort);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error de entrada/salida en el dispositivo GSM Modem.", e.toString(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (NoSuchPortException e) {
			JOptionPane.showMessageDialog(null, "El dispositivo GSM Modem no ha sido hayado.", e.toString(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (PortInUseException e) {
			JOptionPane.showMessageDialog(null, "El dispositivo GSM Modem esta siendo usado por otra aplicacion.", e.toString(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			JOptionPane.showMessageDialog(null, "Se ha solicitado una operacion no soportada por el puerto serial.", e.toString(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}


		try {
			// Ventana Principal
			wp = new FramePrincipal();
			// Host
			ServerSocket server;
			server = new ServerSocket (hostPort);
			while (true) {
				Socket client = server.accept ();
				GPSHandler gps = new GPSHandler(client);
				addConectados(client.getInetAddress().toString(), gps);
				gps.start();
				System.out.println ("Entro " + client.getInetAddress ());
				GPSServer.wp.lblStatus.setText("Entro " + client.getInetAddress ());
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error de entrada/salida escuchando puerto de internet.", e.toString(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	public static void removeConectados(int index){
		x.remove(index);
		GPSServer.gpsh.remove(index);
		GPSServer.wp.lstConectados.setListData(x);
		GPSServer.wp.lblConectados.setText("Conectados ("+x.size()+")");
	}
	public static void removeConectados(String ia, GPSHandler gpsh){
		x.remove(ia);
		GPSServer.gpsh.remove(gpsh);
		GPSServer.wp.lstConectados.setListData(x);
		GPSServer.wp.lblConectados.setText("Conectados ("+x.size()+")");
	}
	public static void addConectados(String ia, GPSHandler gpsh){
		x.add(ia);
		GPSServer.gpsh.add(gpsh);
		GPSServer.wp.lstConectados.setListData(x);
		GPSServer.wp.lblConectados.setText("Conectados ("+x.size()+")");
	}
	public static String addApodo(String apodo, String ia){
		int i = x.indexOf(ia);
		x.set(i, apodo +" - "+ ia);
		GPSServer.wp.lstConectados.setListData(x);
		return x.get(i);
	}
}