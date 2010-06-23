package essenger.cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaChat extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7021695612435115900L;
	JTabbedPane tp = new JTabbedPane();
	JScrollPane spEntrada = new JScrollPane();
	JTextArea txtEntrada = new JTextArea();
	JTextField txtEnvio = new JTextField();
	JPanel pnlMensajes = new JPanel();
	JScrollPane spLogs = new JScrollPane();
	JTextArea txtLogs = new JTextArea();
	public VentanaChat() {
		txtEnvio.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					enviarComando();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		spEntrada.getViewport().add(txtEntrada);
		pnlMensajes.setLayout(new BorderLayout());
		pnlMensajes.add(spEntrada, BorderLayout.CENTER);
		pnlMensajes.add(txtEnvio, BorderLayout.SOUTH);
		spLogs.getViewport().add(txtLogs);
		tp.add("Mensajes", pnlMensajes);
		tp.add("Logs", spLogs);
		getContentPane().add(tp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800,600));
		setVisible(true);
	}
	protected void enviarComando() {
		ClienteChat.c.enviarMensaje(">CMD<MENSAJE="+txtEnvio.getText());
		txtEnvio.setText("");
	}
	public static void main(String[] args) {
		new VentanaChat();
	}
}
