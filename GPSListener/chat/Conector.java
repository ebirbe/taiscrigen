package chat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Conector {
	Socket s;
	BufferedInputStream in;
	BufferedOutputStream out;
	
	public Conector(String host, int port) throws UnknownHostException, IOException {
		s = new Socket(host, port);
		in = new BufferedInputStream(s.getInputStream());
		out = new BufferedOutputStream(s.getOutputStream());
	}
	
	public String leerMensaje() throws IOException{
		String msg = new String();
		while(true){
			Character c = (char) in.read();
			Integer inte = (int)c;
			if(inte<0||inte>127) throw new IOException("Envio caracter de salida. ("+inte+")");

			msg += c.toString();
			if(in.available() == 0 && !c.toString().equals("\n")){
				msg += "\n";
			}
			if(in.available() == 0) break;
		}
		return msg;
	}
	
	public boolean enviarMensaje(String msg){
		boolean exito = true;
		try {
			for(int j=0;j < msg.length();j++){
				out.write((byte)msg.charAt(j));
			}
			out.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
			exito = false;
		}
		return exito;
	}
}
