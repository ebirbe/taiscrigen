package chat;

import java.io.IOException;
import java.net.UnknownHostException;

import contadorBytes.Contador;

import log.MyLogger;

public class ClienteForaneo extends Thread {

	private Conector c;
	private PanelChat pc;

	/**
	 * @param args
	 */
	public ClienteForaneo(PanelChat pc) {
		this.pc = pc;
		start();
	}

	public void run() {
		try {
			c = new Conector("jakygps.no-ip.org", 9008);
			// c = new Conector("jakyavl.homeip.net", 5001);
			// c = new Conector("190.75.108.225", 9008);
			String m = "";
			m = c.leerMensaje();
			imprimir(m);
			imprimir("Enviando inicio de sesion...");
			if (c.enviarMensaje("Soy,jaky,QWPOMNFGTREHORMWRFT"))
				imprimir("Enviado con exito.\n");
			else
				imprimir("No se pudo enviar el inicio de sesion.\n");
			while (true) {
				m = c.leerMensaje();
				imprimir(m);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			imprimir(e.toString());
		} catch (IOException e) {
			System.err.println(e.getCause());
			e.printStackTrace();
			imprimir(e.toString());
		} finally {
		}
	}

	private void imprimir(String msj) {
		String[] v = Contador.dividirMensaje(msj);
		for (int i = 0; i < v.length; i++) {
			pc.agregar(v[i] + "\n");
			MyLogger.escribirLog(Chat.class.getName(), v[i]);
			Contador.contar(v[i]);
		}
	}
}
