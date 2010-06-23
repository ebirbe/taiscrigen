package essenger.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ConectorServidor{
	static Vector<Amigo> listaConectados = new Vector<Amigo>();
	ServerSocket s;
	public static boolean continuar = true;
	
	public ConectorServidor() throws IOException {
		s = new ServerSocket(6000);
		while(continuar){
			generarListaDeAmigos(s.accept());
		}
	}

	private void generarListaDeAmigos(Socket s) {
		try {
			Amigo a = new Amigo(s);
			listaConectados.add(a);
			a.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static void enviarMensaje(String str) throws IOException{
		System.out.println("Enviando:");
		for (Amigo a : listaConectados) {
			a.out.writeUTF(str);
			a.out.flush();
		}
	}
}
