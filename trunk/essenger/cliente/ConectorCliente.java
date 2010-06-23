package essenger.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConectorCliente {
	Socket s;
	DataInputStream in;
	DataOutputStream out;
	
	public ConectorCliente(String host, int port) throws UnknownHostException, IOException {
		s = new Socket(host, port);
		in = new DataInputStream(s.getInputStream());
		out = new DataOutputStream(s.getOutputStream());
	}
	
	public String leerMensaje() throws IOException{
		return in.readUTF();
	}
	
	public boolean enviarMensaje(String msg){
		boolean exito = true;
		try {
			out.writeUTF(msg);
			out.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
			exito = false;
		}
		return exito;
	}
}
