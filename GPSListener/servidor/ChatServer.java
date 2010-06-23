package servidor;
import java.net.*;

import java.io.*;
import java.util.*;

public class  ChatServer  {
	public static Vector<String> x = new Vector<String>();
	public static Vector<GPSHandler> gpsh = new Vector<GPSHandler>();
	public static wPrincipal wp = new wPrincipal();
	public ChatServer (int port) throws IOException {
		ServerSocket server = new ServerSocket (port);
		while (true) {
			Socket client = server.accept ();
			GPSHandler gps = new GPSHandler(client);
			addConectados(client.getInetAddress().toString(), gps);
			gps.start();
			System.out.println ("Entro " + client.getInetAddress ());
		}
	}
	public static void main (String args[]) throws IOException {
		int port = 0;
		try{
			port = Integer.parseInt(args[0]);
			new ChatServer (port);
		}catch (Exception e) {
			System.out.println("Debe indicar un numero de puerto para ejecutar el servidor");
			System.out.println();
			e.printStackTrace();
			System.exit(0);
		}
	}
	public static void removeConectados(int index){
		x.remove(index);
		ChatServer.gpsh.remove(index);
		ChatServer.wp.lstConectados.setListData(x);
		ChatServer.wp.lblConectados.setText("Conectados ("+x.size()+")");
	}
	public static void removeConectados(String ia, GPSHandler gpsh){
		x.remove(ia);
		ChatServer.gpsh.remove(gpsh);
		ChatServer.wp.lstConectados.setListData(x);
		ChatServer.wp.lblConectados.setText("Conectados ("+x.size()+")");
	}
	public static void addConectados(String ia, GPSHandler gpsh){
		x.add(ia);
		ChatServer.gpsh.add(gpsh);
		ChatServer.wp.lstConectados.setListData(x);
		ChatServer.wp.lblConectados.setText("Conectados ("+x.size()+")");
	}
	public static String addApodo(String apodo, String ia){
		int i = x.indexOf(ia);
		x.set(i, apodo +" - "+ ia);
		ChatServer.wp.lstConectados.setListData(x);
		return x.get(i);
	}
}