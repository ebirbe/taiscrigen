package essenger.cliente;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import essenger.comandos.Comando;

public class ClienteChat {
	static ConectorCliente c;
	static VentanaChat v;
	static PantallaNotificacion pn = new PantallaNotificacion(v);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();} 
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}
		try {

			String m;
			//c = new ConectorCliente("localhost", 6000);
			c = new ConectorCliente("jakyavl.homeip.net", 6000);
			v = new VentanaChat();
			String nombre = "";

			while(nombre == ""){
				nombre = JOptionPane.showInputDialog(v,"Introduce tu nombre:");
				if(nombre == null) return;
			}
			c.enviarMensaje(Comando.hacerNombre(nombre));
			while (true) {
				m = c.leerMensaje();
				distribuirMensajes(m);
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
		v.agregarLog(msg);
		msg = msg.trim();
		if(msg.startsWith(Comando.CABECERA)){
			msg = msg.substring(5);
			if(msg.startsWith(Comando.MENSAJE)){
				msg = msg.substring(msg.indexOf("=")+1);
				v.agregarMensaje(msg);
				notificar(msg);
				return;
			}
			if(msg.startsWith(Comando.CONECTADOS)){
				msg = msg.substring(msg.indexOf("=")+1);
				v.agregarConectado(msg);
				return;
			}
		}
	}
	private static void notificar(String msg) {
		if(!v.isEnfocada()){
			pn.insertarTexto(msg);
		}
	}
}
