package servidor;
import java.net.*;
import java.util.Date;
import java.io.*;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cliente.Datos;

import sms.HiloMensajero;
import sms.SerialToGsm;

public class  GPSHandler  extends Thread {
	protected Socket s;
	protected DataInputStream i;
	protected DataOutputStream o;
	boolean privado;
	public String name;
	private String msg;
	public String id = "";
	public boolean leer = true;

	public GPSHandler (Socket s) throws IOException {
		this.s = s;
		i = new DataInputStream (new BufferedInputStream (s.getInputStream ()));
		o = new DataOutputStream (new BufferedOutputStream (s.getOutputStream ()));
	}
	@Override
	public void run () {
		name = s.getInetAddress().toString();
		new HiloContador(this);
		try {
			enviarMensaje(">QID<");
			cicloDeLectura(leer);
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			System.out.println(name + " ha salido de la sala.");
		} finally {
			ChatServer.removeConectados(name, this);
			try {
				finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
			}
		}
	}
	private void cicloDeLectura(boolean leer) throws IOException {
		while (leer) {
			msg = leerMensaje();
			if(id.equals("") && msg.indexOf(";ID=") != -1){
				id = msg.substring(msg.indexOf(";ID=")+4, msg.indexOf("<"));
				comprobarIdUnico();
				name = ChatServer.addApodo(id, name);
			}
			ecribirEnPantalla(name, msg);
		}
	}
	private void ecribirEnPantalla(String name2, String msg2) {
		String linea = new Date().toString() + " - ["+name2+"]\t"+msg2;
		ChatServer.wp.taEntrada.append(linea);
		AutoScroll(ChatServer.wp.taEntrada, ChatServer.wp.spEntrada);
		if(privado){
			ChatServer.wp.taPrivado.append(linea);
			AutoScroll(ChatServer.wp.taPrivado, ChatServer.wp.spPrivado);
		}
		comprobarReportes();
	}
	private void AutoScroll(JTextArea texto, JScrollPane scrollpnl) {
		texto.getCaret().setDot( texto.getText().length() );
		scrollpnl.scrollRectToVisible(texto.getVisibleRect() );
	}
	private void comprobarReportes() {
		String txt;
		try{
			Datos cliente = new Datos(Integer.parseInt(id));
			// Abandono GeoCerca.
			if(msg.indexOf(">REV40") > -1){
				txt = "Rastreo Satelital:\rEl vehiculo " + cliente.id_vehiculo + " ha abandonado la geocerca.\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(SerialToGsm.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(SerialToGsm.stg, "04124662746", txt);
				new HiloMensajero(SerialToGsm.stg, "04128663381", txt);
			}
			// Apagado Vehiculo Cerca Here
			if(msg.indexOf(">REV42") > -1){
				txt = "Rastreo Satelital:\rALERTA: El vehiculo " + cliente.id_vehiculo + " ha sido apagado por violar el Estacionamento Seguro.\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(SerialToGsm.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(SerialToGsm.stg, "04124662746", txt);
				new HiloMensajero(SerialToGsm.stg, "04128663381", txt);
			}
			// Remolcado
			if(msg.indexOf(">REV14") > -1){
				txt = "Rastreo Satelital:\rEsta siendo remolcado el vehiculo " + cliente.id_vehiculo + "\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(SerialToGsm.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(SerialToGsm.stg, "04124662746", txt);
				new HiloMensajero(SerialToGsm.stg, "04128663381", txt);
			}
			// Bateria DESconectada
			if(msg.indexOf(">REV05") > -1){
				txt = "Rastreo Satelital:\rBateria Desconectada en el vehiculo " + cliente.id_vehiculo + "\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(SerialToGsm.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(SerialToGsm.stg, "04124662746", txt);
				new HiloMensajero(SerialToGsm.stg, "04128663381", txt);
			}
			// Bateria Conectada
			if(msg.indexOf(">REV04") > -1){
				txt = "Rastreo Satelital:\rBateria conectada en el vehiculo " + cliente.id_vehiculo + "\rwww.jaky47.com " + cliente.nota;
				new HiloMensajero(SerialToGsm.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(SerialToGsm.stg, "04124662746", txt);
				new HiloMensajero(SerialToGsm.stg, "04128663381", txt);
			}
			// Panico
			if(msg.indexOf(">REV03") > -1){
				txt = "Rastreo Satelital:\rALERTA: Boton de panico presionado en el vehiculo " + cliente.id_vehiculo + "\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(SerialToGsm.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(SerialToGsm.stg, "04124662746", txt);
				new HiloMensajero(SerialToGsm.stg, "04128663381", txt);
			}
		}catch (NumberFormatException e) {
			System.out.println(name + " envio un msj sin reportar ID");
		}
	}
	private void comprobarIdUnico() {
		for (int i = 0; i < ChatServer.gpsh.lastIndexOf(this)-1; i++) {
			if(ChatServer.gpsh.get(i).id.equals(this.id)){
				System.out.println(name + " ha eliminado a su hilo muerto " + ChatServer.gpsh.get(i).name);
				ChatServer.removeConectados(i);
			}
		}
	}
	public String leerMensaje() throws IOException{
		String msg = new String();
		while(true){
			Character c = (char) i.read();
			Integer inte = (int)c;
			if(inte<0||inte>127) throw new IOException(name + " envio peticion de salida.");

			msg += c.toString();
			if(i.available() == 0 && !c.toString().equals("\n")){
				msg += "\n";
			}
			if(i.available() == 0) break;
		}
		return msg;
	}
	public boolean enviarMensaje(String msg){
		boolean exito = true;
		try {
			for(int j=0;j < msg.length();j++){
				o.write((int)msg.charAt(j));
			}
			o.flush (); 
		} catch (IOException ex) {
			ex.printStackTrace();
			exito = false;
		}
		return exito;
	}

	@Override
	protected void finalize() throws Throwable {
		try{
			leer = false;
			s.close();
			o.close();
			i.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
		}
		super.finalize();
	}
}