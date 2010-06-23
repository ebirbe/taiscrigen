package essenger.cliente;

import java.io.IOException;
import java.net.UnknownHostException;

import essenger.servidor.ConectorServidor;

public class ClienteChat {
	static ConectorCliente c;
	static VentanaChat v;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String m;
			c = new ConectorCliente("localhost", 6000);
			v = new VentanaChat();
			c.enviarMensaje(">CMD<NOMBRE=Erick");
			while (true) {
				m = c.leerMensaje();
				distribuirMensajes(m);
				System.out.println(m);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}
	private static void distribuirMensajes(String msg) {
		v.txtLogs.append(msg+"\n");
		msg = msg.trim();
		if(msg.startsWith(">CMD<")){
			msg = msg.substring(5);
			if(msg.startsWith("MENSAJE=")){
				msg = msg.substring(8);
				v.txtEntrada.append(msg+"\n");
				return;
			}
		}
	}
}
