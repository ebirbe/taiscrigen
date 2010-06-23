import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


class Cliente {

	public Cliente(String host, int port) {
		try{
			Socket skCliente = new Socket( host , port );
			
			OutputStream salida = skCliente.getOutputStream();
			DataOutputStream flujoSalida = new DataOutputStream( salida );
			flujoSalida.writeUTF( "Hola soy un nuevo cliente");
			
			InputStream aux = skCliente.getInputStream();
			DataInputStream flujo = new DataInputStream( aux );
			System.out.println( flujo.readUTF() );
			
			skCliente.close();
		} catch( Exception e ) {
			System.out.println( e.getMessage() );
		}
	}

	public static void main(String[] args) {
		new Cliente(args[0], Integer.parseInt(args[1]));
	}
}

