package servidor;
import java.net.*;
import java.io.*;
import java.util.*;

public class  ChatHandler  extends Thread {
	protected Socket s;
	protected DataInputStream i;
	protected DataOutputStream o;

	public ChatHandler (Socket s, Vector<InetAddress> x) throws IOException {
		this.s = s;
		i = new DataInputStream (new BufferedInputStream (s.getInputStream ()));
		o = new DataOutputStream (new BufferedOutputStream (s.getOutputStream ()));
	}

	protected static Vector<ChatHandler> handlers = new Vector<ChatHandler>();

	public void run () {
		InetAddress name = s.getInetAddress ();
		String Listado="";
		try {

			for (int i=0; i < ChatServer.x.size() ; i++)
				Listado=Listado+ChatServer.x.get(i)+"\n";

			handlers.addElement (this);
			broadcast ("->" + Listado);
			while (true) {
				String	msg = i.readUTF ();
				broadcast (name + " - " + msg);
			}
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			System.out.println("Ha salido de la sala.");
		} finally {
			handlers.removeElement (this);
			ChatServer.x.remove(name);
			Listado="";
			for (int i=0; i < ChatServer.x.size() ; i++)
				Listado=Listado+ChatServer.x.get(i)+"\n";
			broadcast ("<-" + Listado);
			try {
				s.close ();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	protected static void broadcast (String message) {
		synchronized (handlers) {
			Enumeration<ChatHandler> e = handlers.elements();
			while (e.hasMoreElements ()) {
				ChatHandler c = e.nextElement ();
				try {
					synchronized (c.o) {
						c.o.writeBytes(message);
					}
					c.o.flush ();
				} catch (IOException ex) {
					c.stop();
				}
			}
		}
	}
}