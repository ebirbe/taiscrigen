package essenger.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import essenger.comandos.Comando;

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
			System.out.println(s.getInetAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public synchronized static String imprimirConectados() {
		String lista=Comando.hacerConectados();
		for (Amigo a : listaConectados) {
			lista += a.nombre + "\n";
		}
		return lista;
	}
	public synchronized static boolean nombreRepetido(String nombre) {
		boolean repetido = false;
		for (Amigo a : listaConectados) {
			if(a.nombre.toUpperCase().equals(nombre.toUpperCase())){
				repetido = true;
				break;
			}
		}
		return repetido;
	}
	public synchronized static void enviarMensaje(String str) {
		for (Amigo a : listaConectados) {
			try {
				a.enviarMensaje(str);
				a.enviarMensaje(imprimirConectados());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public synchronized static void desconectar(Amigo a){
		listaConectados.remove(a);
	}
}
