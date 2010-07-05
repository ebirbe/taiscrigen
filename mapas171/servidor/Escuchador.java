package org.mapas171.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Escuchador {
	int hostPort = 5000;

	public Escuchador() {

		try {
			// Host
			ServerSocket server;
			server = new ServerSocket(hostPort);
			while (true) {
				Socket client = server.accept();
				GPSHandler gps = new GPSHandler(client);
				gps.start();
				System.out.println("Entro " + client.getInetAddress());
			}
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"El servidor no se pudo inicializar.\nNo habra actualizacion de ubicacion para los GPS.",
							"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Escuchador();
	}
}
