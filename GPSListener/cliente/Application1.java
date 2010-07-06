package cliente;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

/**
 * package18.Application1
 * <P>
 * 
 * @author lomaky
 */
public class Application1 {

	public Application1(String host, int port) throws IOException {
		Socket s = new Socket(host, port);
		Frame frame = new lomakyChat("Chat " + host + ":" + port,
				s.getInputStream(), s.getOutputStream());

		// Frame frame = new lomakyChat();
		// Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		try {
			new Application1(args[0], Integer.parseInt(args[1]));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Debe indicar un host y un puerto");
			System.out.println("USO:");
			System.out.println("appname HOST PORT SERIAL_PORT");
			System.exit(0);
		}
	}
}
