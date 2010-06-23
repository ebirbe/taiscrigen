package gps;
import gps.interprete.Comando;
import gps.interprete.ComandoEV;
import gps.interprete.ExcepcionComandoInvalido;
import gps.interprete.SelectorDeComandos;

import java.net.*;
import java.util.Date;
import java.io.*;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cliente.Datos;

import servidor.GPSServer;
import sms.HiloMensajero;

public class  GPSHandler  extends Thread {
	protected Socket s;
	protected DataInputStream i;
	protected DataOutputStream o;
	public boolean privado;
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
			System.out.println(name + " ya no esta a la vista.");
			GPSServer.wp.lblStatus.setText(ex.getMessage()+" "+name + " ya no esta a la vista.");
		} finally {
			GPSServer.removeConectados(name, this);
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
				name = GPSServer.addApodo(id, name);
			}
			ecribirEnPantalla(name, msg);
		}
	}
	private void ecribirEnPantalla(String name2, String msg2) {
		// Comando en formato RAW
		String linea = new Date().toString() + " - ["+name2+"]\t"+msg2;
		GPSServer.wp.taEntrada.append(linea);
		
		// Comando en formato Humano
		boolean reportar = true;
		Comando c = null;
		try {
			SelectorDeComandos sel;
			sel = new SelectorDeComandos(msg2);
			c = sel.seleccionarComando();
			if(c != null){
				GPSServer.wp.taEntrada.append(c.imprimir());
				
				// Filtrado de eventos.
				if(c.getClass().equals(ComandoEV.class)){
					//ComandoEV ev = (ComandoEV) c;
					enviarMensaje(">QXADM1<");
					/*if(ev.getFuente().equals("9") || ev.getFuente().equals("0")){
						if(ev.getEdad().equals("0"))	
							reportar = false;
					}*/
				}
			}
		} catch (ExcepcionComandoInvalido e) {
			System.err.println(e);
			GPSServer.wp.lblStatus.setText(e.toString());
		}
		
		AutoScroll(GPSServer.wp.taEntrada, GPSServer.wp.spEntrada);
		if(privado){
			GPSServer.wp.taPrivado.append(linea);
			if(c != null)
				GPSServer.wp.taPrivado.append(c.imprimir());
			if(!reportar) GPSServer.wp.taPrivado.append("Este evento es filtrado y no genera SMS.");
			AutoScroll(GPSServer.wp.taPrivado, GPSServer.wp.spPrivado);
		}
		
		if(reportar)	comprobarReportes();
	}
	
	private void AutoScroll(JTextArea texto, JScrollPane scrollpnl) {
		// Si el autoscroll esta inactivo no se hace nada
		if(GPSServer.wp.chkMniAutoScrool.getState() == false) return;
		
		texto.getCaret().setDot( texto.getText().length() );
		scrollpnl.scrollRectToVisible(texto.getVisibleRect() );
	}
	private void comprobarReportes() {
		String txt;
		try{
			Datos cliente = new Datos(Integer.parseInt(id));
			// Desactivado Estacionamiento Seguro
			if(msg.indexOf(">REV44") > -1){
				txt = "Rastreo Satelital:\rEl vehiculo " + cliente.id_vehiculo + " ha desactivado el Estacionmiento Seguro.\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(GPSServer.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(GPSServer.stg, "04124662746", txt);
			}
			// Abandono GeoCerca.
			if(msg.indexOf(">REV40") > -1){
				txt = "Rastreo Satelital:\rEl vehiculo " + cliente.id_vehiculo + " ha abandonado la geocerca.\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(GPSServer.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(GPSServer.stg, "04124662746", txt);
			}
			// Apagado Vehiculo Cerca Here
			if(msg.indexOf(">REV42") > -1){
				txt = "Rastreo Satelital:\rALERTA: El vehiculo " + cliente.id_vehiculo + " ha sido apagado por violar el Estacionamento Seguro.\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(GPSServer.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(GPSServer.stg, "04124662746", txt);
			}
			// Activada Estacionamiento Seguro
			if(msg.indexOf(">REV41") > -1){
				txt = "Rastreo Satelital:\rEl vehiculo " + cliente.id_vehiculo + " ha activado el Estacionmiento Seguro.\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(GPSServer.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(GPSServer.stg, "04124662746", txt);
			}
			// Remolcado
			if(msg.indexOf(">REV14") > -1){
				txt = "Rastreo Satelital:\rEsta siendo remolcado el vehiculo " + cliente.id_vehiculo + "\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(GPSServer.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(GPSServer.stg, "04124662746", txt);
			}
			// Bateria DESconectada
			if(msg.indexOf(">REV05") > -1){
				txt = "Rastreo Satelital:\rBateria Desconectada en el vehiculo " + cliente.id_vehiculo + "\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(GPSServer.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(GPSServer.stg, "04124662746", txt);
			}
			// Bateria Conectada
			if(msg.indexOf(">REV04") > -1){
				txt = "Rastreo Satelital:\rBateria conectada en el vehiculo " + cliente.id_vehiculo + "\rwww.jaky47.com " + cliente.nota;
				new HiloMensajero(GPSServer.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(GPSServer.stg, "04124662746", txt);
			}
			// Panico
			if(msg.indexOf(">REV03") > -1){
				txt = "Rastreo Satelital:\rALERTA: Boton de panico presionado en el vehiculo " + cliente.id_vehiculo + "\rwww.jaky47.com" + cliente.nota;
				new HiloMensajero(GPSServer.stg, cliente.tlf_cliente, txt);
				new HiloMensajero(GPSServer.stg, "04124662746", txt);
			}
		}catch (NumberFormatException e) {
			System.out.println(name + " envio un msj sin reportar ID");
			GPSServer.wp.lblStatus.setText(name + " envio un msj sin reportar ID");
		}
	}
	private void comprobarIdUnico() {
		for (int i = 0; i < GPSServer.gpsh.lastIndexOf(this)-1; i++) {
			if(GPSServer.gpsh.get(i).id.equals(this.id)){
				System.out.println(name + " ha eliminado a su hilo muerto " + GPSServer.gpsh.get(i).name);
				GPSServer.wp.lblStatus.setText(name + " ha eliminado a su hilo muerto " + GPSServer.gpsh.get(i).name);
				GPSServer.removeConectados(i);
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