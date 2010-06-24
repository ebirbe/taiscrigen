package essenger.servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import essenger.comandos.Comando;
import essenger.ui.Util;

public class Amigo extends Thread{
	Socket s;
	String nombre;
	Date conectadoDesde;
	DataInputStream in;
	DataOutputStream out;
	private String ultimoMensaje = "";
	boolean leer = true;
	public Amigo(Socket s) throws IOException {
		this.s = s;
		in = new DataInputStream (new BufferedInputStream (s.getInputStream()));
		out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
		nombre = "";
		conectadoDesde = new Date();
	}
	@Override
	public void run() {
		try {
			enviarMensaje(Comando.hacerMensaje(Util.horaActual()+"Bienvenido(a) al Essenger."));
			CicloLectura();
		} catch (IOException e) {
			System.out.println(this.nombre + " se fue.");
			ConectorServidor.enviarMensaje(Comando.hacerMensaje(Util.horaActual()+"<-- "+this.nombre + " se fue."));
			ConectorServidor.desconectar(this);
		}
	}
	private void CicloLectura() throws IOException {
		String msg;
		int repetidos = 0;
		while(leer){
			msg = in.readUTF();
			System.out.println(msg);
			filtrarComando(msg);

			if(ultimoMensaje.equals(msg)) repetidos++;
			else repetidos = 0;
			ultimoMensaje = msg;
			if(repetidos >= 20) throw new IOException("Muchos mensajes repetidos.");
		}
	}
	public void enviarMensaje(String msg) throws IOException {
		out.writeUTF(msg);
		out.flush();
	}
	private void filtrarComando(String msg) throws IOException {
		msg = msg.trim();
		if(msg.startsWith(Comando.CABECERA)){
			msg = msg.substring(msg.indexOf("<")+1);
			if(msg.startsWith(Comando.NOMBRE)){
				this.nombre = msg.substring(msg.indexOf("=")+1);
				System.out.println("Entro: " + this.nombre);
				ConectorServidor.enviarMensaje(Comando.hacerMensaje(Util.horaActual()+"--> Entro: " + this.nombre));
				return;
			}
			if(msg.startsWith(Comando.MENSAJE)){
				msg = msg.substring(msg.indexOf("=")+1);
				msg = Comando.hacerMensaje(Util.horaActual()+(this.nombre + " dice: ").toUpperCase() + msg);
				System.out.println(msg);
				ConectorServidor.enviarMensaje(msg);
				return;
			}
		}
	}
}