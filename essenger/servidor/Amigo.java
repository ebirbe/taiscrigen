package essenger.servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class Amigo extends Thread{
	Socket s;
	String nombre;
	Date conectadoDesde;
	DataInputStream in;
	DataOutputStream out;
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
			CicloLectura();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println(nombre + " se fue.");
		}
	}
	private void CicloLectura() throws IOException {
		String msg;
		while(leer){
			msg = in.readUTF();
			System.out.println(msg);
			filtrarComando(msg);
		}
	}
	private void filtrarComando(String msg) throws IOException {
		msg = msg.trim();
		if(msg.startsWith(">CMD<")){
			msg = msg.substring(5);
			if(msg.startsWith("NOMBRE=")){
				this.nombre = msg.substring(7);
				System.out.println("Entro: " + this.nombre);
				ConectorServidor.enviarMensaje(("Entro: " + this.nombre).toUpperCase());
				return;
			}
			if(msg.startsWith("MENSAJE=")){
				msg = msg.substring(8);
				msg = (">CMD<MENSAJE="+this.nombre + " dice: ").toUpperCase() + msg;
				System.out.println(msg);
				ConectorServidor.enviarMensaje(msg);
				return;
			}
		}
	}
}