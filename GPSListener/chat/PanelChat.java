package chat;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import servidor.GPSServer;

public class PanelChat extends JPanel {
	private static final long serialVersionUID = 2549293132216638258L;
	JTextArea txtRespuesta = new JTextArea();
	JScrollPane jspRespuesta = new JScrollPane();
	public PanelChat() {
		txtRespuesta.setEditable(false);
		jspRespuesta.getViewport().add(txtRespuesta);
		setLayout(new CardLayout());
		add(jspRespuesta);
		setVisible(true);
	}
	
	public void agregar(String msg){
		txtRespuesta.append(msg);
		AutoScroll(txtRespuesta, jspRespuesta);
	}
	
	private void AutoScroll(JTextArea texto, JScrollPane scrollpnl) {
		// Si el autoscroll esta inactivo no se hace nada
		if(GPSServer.wp.chkMniAutoScrool.getState() == false) return;
		
		texto.getCaret().setDot( texto.getText().length() );
		scrollpnl.scrollRectToVisible(texto.getVisibleRect() );
	}
}
