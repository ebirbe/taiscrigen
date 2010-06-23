import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Servidor {
	public Servidor(int port) {
		try {
			ServerSocket skServidor = new ServerSocket( port );
			System.out.println("Escucho el puerto " + port );
			for ( int numCli = 0; numCli >= 0; numCli++ ) {
				
				Socket skCliente = skServidor.accept(); // Crea objeto
				
				OutputStream aux = skCliente.getOutputStream();
				DataOutputStream flujo= new DataOutputStream( aux );
				flujo.writeUTF( "Hola cliente " + numCli );
				
				InputStream entrada = skCliente.getInputStream();
				DataInputStream flujoEntrada = new DataInputStream(entrada);
				flujo.writeUTF(flujoEntrada.readUTF());
				
				skCliente.close();
			}
			System.out.println("Demasiados clientes por hoy");
		} catch( Exception e ) {
			System.out.println( e.getMessage() );
		}
	}
	public static void main( String[] args ) {
		new Servidor(Integer.parseInt(args[0]));
	}
}