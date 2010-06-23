package chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrameChat extends JFrame {
	private static final long serialVersionUID = -5777423832543978363L;
	JTextArea txtRespuesta = new JTextArea();
	JScrollPane jspRespuesta = new JScrollPane();
	JTextField txtEnvio = new JTextField();
	JButton btnEnviar = new JButton("Enviar");
	JPanel pnlEnvio = new JPanel();
	public FrameChat() {
		txtEnvio.setPreferredSize(new Dimension(500, 20));
		txtRespuesta.setPreferredSize(new Dimension(800,600));
		jspRespuesta.getViewport().add(txtRespuesta);
		pnlEnvio.setLayout(new FlowLayout());
		pnlEnvio.add(txtEnvio);
		pnlEnvio.add(btnEnviar);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jspRespuesta, BorderLayout.CENTER);
		getContentPane().add(pnlEnvio, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new FrameChat();
	}
}
