package essenger.cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import essenger.comandos.Comando;
import essenger.ui.Util;

public class VentanaChat extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7021695612435115900L;
	private boolean enfocada = true;
	public boolean isEnfocada() {
		return enfocada;
	}
	JScrollPane spConectados = new JScrollPane();
	JTextArea txtConectados = new JTextArea();
	JTabbedPane tp = new JTabbedPane();
	JScrollPane spEntrada = new JScrollPane();
	JTextArea txtEntrada = new JTextArea();
	JTextField txtEnvio = new JTextField();
	JPanel pnlMensajes = new JPanel();
	JScrollPane spLogs = new JScrollPane();
	JTextArea txtLogs = new JTextArea();
	public VentanaChat() {
		txtConectados.setEditable(false);
		txtEntrada.setEditable(false);
		txtEntrada.setLineWrap(true);
		txtEntrada.setWrapStyleWord(true);
		txtLogs.setEditable(false);
		spConectados.getViewport().setLayout(new BorderLayout());
		spConectados.getViewport().add(new Label("Conectados"), BorderLayout.NORTH);
		spConectados.getViewport().add(txtConectados, BorderLayout.CENTER);
		spConectados.setPreferredSize(new Dimension(100,0));
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
		spEntrada.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent arg0) {}
			@Override
			public void componentResized(ComponentEvent arg0) {
				txtEntrada.setSize(spEntrada.getWidth(), spEntrada.getViewport().getHeight());
			}
			@Override
			public void componentMoved(ComponentEvent arg0) {}
			@Override
			public void componentHidden(ComponentEvent arg0) {}
		});
		spEntrada.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spEntrada.getViewport().add(txtEntrada);
		pnlMensajes.setLayout(new BorderLayout());
		pnlMensajes.add(spEntrada, BorderLayout.CENTER);
		pnlMensajes.add(txtEnvio, BorderLayout.SOUTH);
		spLogs.getViewport().add(txtLogs);
		tp.add("Mensajes", pnlMensajes);
		tp.add("Logs", spLogs);
		addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(WindowEvent arg0) {
				enfocada = false;
			}
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				enfocada = true;
			}
		});
		getContentPane().add(spConectados, BorderLayout.WEST);
		getContentPane().add(tp, BorderLayout.CENTER);
		getContentPane().add(tp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800,600));
		setTitle("Essenger");
		Util.centrarVentana(this);
		setVisible(true);
		txtEnvio.requestFocusInWindow();
	}
	protected void enviarComando() {
		if(txtEnvio.getText().equals("")) return;
		ClienteChat.c.enviarMensaje(Comando.hacerMensaje(txtEnvio.getText()));
		txtEnvio.setText("");
		txtEnvio.requestFocusInWindow();
	}
	public static void main(String[] args) {
		new VentanaChat();
	}
	public void agregarConectado(String msg){
		txtConectados.setText("");
		agregar(msg, txtConectados, spConectados);
	}
	public void agregarMensaje(String msg){
		agregar(msg, txtEntrada, spEntrada);
	}
	public void agregarLog(String msg){
		agregar(msg, txtLogs, spLogs);
	}
	private void agregar(String msg, JTextArea jta, JScrollPane jsp){
		jta.append(msg+"\n");
		Util.AutoScroll(jta, jsp);
	}
}
