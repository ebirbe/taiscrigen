package essenger.cliente;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import essenger.sonido.ReproductorWAV;
import essenger.ui.Util;

public class PantallaNotificacion extends Thread {
	static ReproductorWAV sonido;
	private static boolean hayHilo = false;
	private static final int TIEMPO_MAXIMO = 15;
	private static int t = TIEMPO_MAXIMO;
	boolean activo = false;
	public boolean isActivo() {
		return activo;
	}
	String mensaje = "";
	JWindow frame;
	JPanel pnlMsg;
	public PantallaNotificacion(JFrame parent) {
		pnlMsg = new JPanel();
		sonido = new ReproductorWAV();
		frame = new JWindow(parent);
		CardLayout cLay = new CardLayout();
		cLay.setHgap(10);
		cLay.setVgap(10);
		pnlMsg.setLayout(new BoxLayout(pnlMsg, BoxLayout.Y_AXIS));
		frame.add(pnlMsg);
		frame.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cerrar();
			}
		});
		frame.setFocusableWindowState(false);
		frame.setFocusable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(Color.ORANGE);
		pnlMsg.setBackground(pnlMsg.getParent().getBackground());
		frame.setLayout(cLay);
	}
	public void insertarTexto(String msg){
		//if(!activo) pnlMsg.add(time);
		if(msg.indexOf("\t") > -1){
			final int TOPE = 70;
			int max;
			int tam = msg.length();

			if(tam >= TOPE) max = TOPE;
			else max = msg.length();

			msg = msg.substring(msg.indexOf("\t"),max);
			if(tam > TOPE) msg = msg.trim() + "...";
		}
		pnlMsg.add(new JLabel(msg));
		mostrar();
	}
	@Override
	public void run() {
		cerrar();
	}
	private void mostrar(){
		frame.pack();
		Util.pantallaInferiorDerecha(frame);
		frame.setVisible(true);
		activo = true;
		new Contador().start();
		new AlternadorDeTitulo().start();
		new Sonido().start();
	}

	private void cerrar() {
		activo = false;
		frame.setVisible(false);
		pnlMsg.removeAll();
	}
	public static void main(String[] args) {
		System.out.println("");
		PantallaNotificacion p = new PantallaNotificacion(null);
		p.insertarTexto("Holaaaaaaaaaaaaaaaaaaaaaaaaaa");
		p.insertarTexto("Holaaaaaaaaaaaaaaaaaaaaaaaaaa");
		p.insertarTexto("Holaaaaaaaaaaaaaaaaaaaaaaaaaa");
		p.insertarTexto("Holaaaaaaaaaaaaaaaaaaaaaaaaaa");
		p.insertarTexto("\tHolaaaaaaaaaaaaaaaaaaaaaaaaaasdfsdfsdfsdfsdfsdfsdfsdfsdsdfsdfsdfsdfsfsdfsdfsdfsdfsdfsdfsdfs");
	}

	private class Contador extends Thread{
		@Override
		public void run() {
			t = TIEMPO_MAXIMO;
			if (hayHilo) return;
			hayHilo = true;
			try {
				System.out.print(t+"-");
				while(t >= 0 && activo){
					sleep(1000);
					t--;
				}
				cerrar();
			}
			catch (InterruptedException e) {e.printStackTrace();}
			hayHilo = false;
		}
	}
	private class Sonido extends Thread{
		public void run() {sonido.reproducir();};
	}
	private class AlternadorDeTitulo extends Thread{
		@Override
		public void run() {
			try {
				String tit = ClienteChat.v.getTitle();
				while(!ClienteChat.v.isEnfocada()){
					ClienteChat.v.setTitle("Nuevo Mensaje");
					sleep(2000);
					ClienteChat.v.setTitle(tit);
					sleep(2000);
				}
				// Devolvemos el titulo anterior
				ClienteChat.v.setTitle(tit);
			}
			catch (InterruptedException e) {e.printStackTrace();}
			catch (NullPointerException e) {}
		}
	}
}
