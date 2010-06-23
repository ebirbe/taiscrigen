package servidor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class wPrincipal extends JFrame {
	private static final long serialVersionUID = -1386212642735384242L;

	JLabel lblConectados = new JLabel("Conectados");
	JScrollPane spConectados = new JScrollPane();
	JPanel pnlLateral = new JPanel();
	JPanel pnlOpciones = new JPanel();
	JToggleButton btnActivar = new JToggleButton("Activar");
	JList lstConectados = new JList();
	JSplitPane splPanel = new JSplitPane();
	JSplitPane splPanel2 = new JSplitPane();
	JPanel pnlEntrada = new JPanel();
	JScrollPane spEntrada = new JScrollPane();
	JTextArea taEntrada = new JTextArea();
	JPanel pnlprivado = new JPanel();
	JScrollPane spPrivado = new JScrollPane();
	JTextArea taPrivado = new JTextArea();
	JPanel pnlComandos = new JPanel();
	JButton btnQid = new JButton("ID");
	JButton btnQcp = new JButton("Posicion");
	JButton btnQal = new JButton("Altura");
	JButton btnXp11 = new JButton("Apagar");
	JButton btnXp10 = new JButton("Encender");
	JButton btnU001 = new JButton("Apag. Seguro");
	JButton btnU011 = new JButton("Est. ON");
	JButton btnU010 = new JButton("Est. OFF");
	JLabel lblPara = new JLabel();
	JTextField txtComando = new JTextField();
	JCheckBox chkForzarMayuscula = new JCheckBox("Mayuscula");
	JButton btnEnviar = new JButton("Enviar");
	JButton btnBorrar = new JButton("Borrar");
	JButton btnSalir = new JButton("Salir");
	JPanel pnlEnvio = new JPanel();

	String valorSeleccionado = new String();
	int indiceSeleccionado;
	GPSHandler gpsh;

	public wPrincipal() {
		btnActivar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(btnActivar.isSelected()){
					seleccionarEquipo();
				}else{
					deseleccionarEquipo();
				}
			}
		});
		pnlOpciones.add(btnActivar);
		pnlLateral.setLayout(new BorderLayout());
		pnlLateral.add(lblConectados, BorderLayout.NORTH);
		pnlLateral.add(lstConectados, BorderLayout.CENTER);
		pnlLateral.add(pnlOpciones, BorderLayout.SOUTH);
		spConectados.getViewport().add(pnlLateral);
		spConectados.setPreferredSize(new Dimension(200, 100));
		taEntrada.setEditable(false);
		spEntrada.setPreferredSize(new Dimension(100,400));
		spEntrada.getViewport().add(taEntrada);
		pnlEntrada.setLayout(new BorderLayout());
		pnlEntrada.add(new JLabel("Todos"), BorderLayout.NORTH);
		pnlEntrada.add(spEntrada, BorderLayout.CENTER);
		taPrivado.setEditable(false);
		spPrivado.getViewport().add(taPrivado);
		btnQid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtComando.setText("QID");
				enviarComando();
			}
		});
		btnQcp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtComando.setText("QCP");
				enviarComando();
			}
		});
		btnQal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtComando.setText("QAL");
				enviarComando();
			}
		});
		btnXp11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtComando.setText("SSSXP11");
				enviarComando();
			}
		});
		btnXp10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtComando.setText("SSSXP10");
				enviarComando();
			}
		});
		btnU001.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtComando.setText("SSSU001");
				enviarComando();
			}
		});
		btnU011.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtComando.setText("SSSU011");
				enviarComando();
			}
		});
		btnU010.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtComando.setText("SSSU010");
				enviarComando();
			}
		});
		pnlComandos.setVisible(false);
		pnlComandos.setLayout(new GridLayout(2, 4));
		pnlComandos.add(btnQid);
		pnlComandos.add(btnQcp);
		pnlComandos.add(btnQal);
		pnlComandos.add(btnU001);
		pnlComandos.add(btnXp11);
		pnlComandos.add(btnXp10);
		pnlComandos.add(btnU011);
		pnlComandos.add(btnU010);
		pnlprivado.setLayout(new BorderLayout());
		pnlprivado.add(new JLabel("Privado"), BorderLayout.NORTH);
		pnlprivado.add(spPrivado, BorderLayout.CENTER);
		pnlprivado.add(pnlComandos, BorderLayout.SOUTH);
		splPanel2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splPanel2.add(pnlEntrada, JSplitPane.TOP);
		splPanel2.add(pnlprivado, JSplitPane.BOTTOM);
		splPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splPanel.add(spConectados, JSplitPane.LEFT);
		splPanel.add(splPanel2, JSplitPane.RIGHT);
		lblPara.setVisible(false);
		chkForzarMayuscula.setVisible(false);
		chkForzarMayuscula.setSelected(true);
		txtComando.setVisible(false);
		txtComando.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					enviarComando();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {}
		});
		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taEntrada.setText("");
				taPrivado.setText("");
			}
		});
		btnEnviar.setVisible(false);
		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enviarComando();
			}
		});
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		pnlEnvio.setLayout(new BoxLayout(pnlEnvio, BoxLayout.X_AXIS));
		pnlEnvio.add(lblPara);
		pnlEnvio.add(chkForzarMayuscula);
		pnlEnvio.add(txtComando);
		pnlEnvio.add(btnEnviar);
		pnlEnvio.add(btnBorrar);
		pnlEnvio.add(btnSalir);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(splPanel, BorderLayout.CENTER);
		getContentPane().add(pnlEnvio, BorderLayout.SOUTH);
		setTitle("GPSListener");
		// Size the window
		setSize(new Dimension(800, 600));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		// Center the window
		setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		// Close the window
		addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { System.exit(0); } });
		setVisible(true);
	}

	void seleccionarEquipo(){
		try {
			indiceSeleccionado = lstConectados.getSelectedIndex();
			valorSeleccionado = lstConectados.getSelectedValue().toString();

			ChatServer.gpsh.get(lstConectados.getSelectedIndex()).privado = true;

			lblPara.setText(lstConectados.getSelectedValue().toString()+": ");
			lblPara.setVisible(true);
			chkForzarMayuscula.setVisible(true);
			pnlComandos.setVisible(true);
			btnEnviar.setVisible(true);
			txtComando.setVisible(true);
			lstConectados.setEnabled(false);
			txtComando.requestFocusInWindow();
			btnActivar.setText("Desactivar");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Selecione un item de la lista.");
			btnActivar.setSelected(false);
		}
	}

	void deseleccionarEquipo(){
		try{
			ChatServer.gpsh.get(indiceSeleccionado).privado = false;
		}catch (Exception e) {
			e.getCause();
			e.getMessage();
		}
		lblPara.setText("");
		txtComando.setText("");
		taPrivado.setText("");
		lblPara.setVisible(false);
		chkForzarMayuscula.setVisible(false);
		pnlComandos.setVisible(false);
		btnEnviar.setVisible(false);
		txtComando.setVisible(false);
		lstConectados.setEnabled(true);
		btnActivar.setText("Activar");
		btnActivar.setSelected(false);
	}

	void enviarComando(){
		int i;
		
		String cmd;
		if(chkForzarMayuscula.isSelected())
			cmd = ">"+txtComando.getText().toUpperCase()+"<";
		else
			cmd = ">"+txtComando.getText()+"<";
		
		try {
			if(!ChatServer.gpsh.get(indiceSeleccionado).name.equals(valorSeleccionado))
				throw new Exception("El GPS solicitado cambio de ID en la lista.");
			ChatServer.gpsh.get(indiceSeleccionado).enviarMensaje(cmd);
			taPrivado.append(cmd+"\n");
			txtComando.setText("");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			boolean encontrado = false;
			for(i = 0; i < lstConectados.getModel().getSize(); i++) {
				if(lstConectados.getModel().getElementAt(i).equals(valorSeleccionado)){
					indiceSeleccionado = i;
					encontrado = true;
					break;
				}
			}
			if(!encontrado){
				JOptionPane.showMessageDialog(null, "El equipo parece haberse desconectado.");
				deseleccionarEquipo();
			}else{
				lstConectados.setSelectedIndex(i);
				seleccionarEquipo();
				enviarComando();
			}
		}
	}
	
	public static void main(String[] args) {
		new wPrincipal();
	}
}
