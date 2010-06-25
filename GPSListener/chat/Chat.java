package chat;

import java.io.IOException;
import java.net.UnknownHostException;

public class Chat {
	
	static FrameChat f;
	static Conector c;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			f = new FrameChat();
			c = new Conector("jakygps.no-ip.org", 9008);
			//c = new Conector("jakyavl.homeip.net", 5001);
			//c = new Conector("190.75.108.225", 9008);
			String m = "";
			m = c.leerMensaje();
			imprimir(m);
			imprimir("Enviando inicio de sesion...");
			if(c.enviarMensaje("Idenficacion,jaky,QWPOMNFGTREHORMWRFT"))
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
		}finally{
			//System.exit(0);
		}
	}
	private static void imprimir(String msj){
		f.txtRespuesta.append(msj);
	}
}
