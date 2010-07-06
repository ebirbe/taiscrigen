package servidor;

import gps.GPSHandler;

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
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import chat.PanelChat;

import log.MyLogger;

public class FramePrincipal extends JFrame {
	private static final long serialVersionUID = -1386212642735384242L;

	JMenuBar menuBar = new JMenuBar();
	JMenu mnAcciones = new JMenu("Acciones");
	JMenuItem mniSalir = new JMenuItem("Salir");
	JMenuItem mniBorrar = new JMenuItem("Borrar");
	JMenu mnOpciones = new JMenu("Opciones");
	public JCheckBoxMenuItem chkMniAutoScrool = new JCheckBoxMenuItem(
			"Autoscroll", true);

	JLabel lblConectados = new JLabel("Conectados");
	JScrollPane spConectados = new JScrollPane();
	JPanel pnlLateral = new JPanel();
	JPanel pnlOpciones = new JPanel();
	JToggleButton btnActivar = new JToggleButton("Activar");
	JList lstConectados = new JList();
	JSplitPane splPanel = new JSplitPane();
	JSplitPane splPanel2 = new JSplitPane();
	JPanel pnlEntrada = new JPanel();
	public JScrollPane spEntrada = new JScrollPane();
	public JTextArea taEntrada = new JTextArea();
	JPanel pnlPrivado = new JPanel();
	public JScrollPane spPrivado = new JScrollPane();
	public JTextArea taPrivado = new JTextArea();
	JPanel pnlComandos = new JPanel();
	JPanel pnlComandosBtn = new JPanel();
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
	JCheckBoxMenuItem chkForzarMayuscula = new JCheckBoxMenuItem("Mayuscula",
			true);
	JButton btnEnviar = new JButton("Enviar");
	JPanel pnlEnvio = new JPanel();
	public JLabel lblStatus = new JLabel();
	JPanel pnlStatus = new JPanel();

	JTabbedPane tpnlContenido = new JTabbedPane();
	PanelChat pnlForaneo = new PanelChat();

	String valorSeleccionado = new String();
	int indiceSeleccionado;
	GPSHandler gpsh;

	public FramePrincipal() {
		mniBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taEntrada.setText("");
				taPrivado.setText("");
			}
		});
		mniSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnAcciones.add(mniBorrar);
		mnAcciones.add(mniSalir);
		mnOpciones.add(chkMniAutoScrool);
		mnOpciones.add(chkForzarMayuscula);
		menuBar.add(mnAcciones);
		menuBar.add(mnOpciones);

		btnActivar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnActivar.isSelected()) {
					seleccionarEquipo();
				} else {
					deseleccionarEquipo();
				}
			}
		});
		lstConectados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pnlOpciones.add(btnActivar);
		pnlLateral.setLayout(new BorderLayout());
		pnlLateral.add(lblConectados, BorderLayout.NORTH);
		pnlLateral.add(lstConectados, BorderLayout.CENTER);
		pnlLateral.add(pnlOpciones, BorderLayout.SOUTH);
		spConectados.getViewport().add(pnlLateral);
		spConectados.setPreferredSize(new Dimension(200, 100));
		taEntrada.setEditable(false);
		spEntrada.setPreferredSize(new Dimension(100, 400));
		spEntrada.getViewport().add(taEntrada);
		pnlEntrada.setLayout(new BorderLayout());
		pnlEntrada.add(new JLabel("General"), BorderLayout.NORTH);
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
				int op = JOptionPane.showConfirmDialog(FramePrincipal.this,
						"Seguro que desea apagar este vehiculo?",
						"Apagado Directo", JOptionPane.OK_CANCEL_OPTION);
				if (op == JOptionPane.OK_OPTION) {
					txtComando.setText("SSSXP11");
					enviarComando();
				}
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
				int op = JOptionPane.showConfirmDialog(FramePrincipal.this,
						"Seguro que desea apagar este vehiculo?",
						"Apagado Seguro", JOptionPane.OK_CANCEL_OPTION);
				if (op == JOptionPane.OK_OPTION) {
					txtComando.setText("SSSU001");
					enviarComando();
				}
			}
		});
		btnU011.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(FramePrincipal.this,
						"Activar estacionamiento seguro para este vehiculo?",
						null, JOptionPane.OK_CANCEL_OPTION);
				if (op == JOptionPane.OK_OPTION) {
					txtComando.setText("SSSU011");
					enviarComando();
				}
			}
		});
		btnU010.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane
						.showConfirmDialog(
								FramePrincipal.this,
								"Desactivar estacionamiento seguro para este vehiculo?",
								null, JOptionPane.OK_CANCEL_OPTION);
				if (op == JOptionPane.OK_OPTION) {
					txtComando.setText("SSSU010");
					enviarComando();
				}
			}
		});
		pnlComandosBtn.setLayout(new GridLayout(2, 4));
		pnlComandosBtn.add(btnQid);
		pnlComandosBtn.add(btnQcp);
		pnlComandosBtn.add(btnQal);
		pnlComandosBtn.add(btnU001);
		pnlComandosBtn.add(btnXp11);
		pnlComandosBtn.add(btnXp10);
		pnlComandosBtn.add(btnU011);
		pnlComandosBtn.add(btnU010);
		pnlEnvio.setLayout(new BoxLayout(pnlEnvio, BoxLayout.X_AXIS));
		pnlEnvio.add(txtComando);
		pnlEnvio.add(btnEnviar);
		pnlComandos.setLayout(new BoxLayout(pnlComandos, BoxLayout.Y_AXIS));
		pnlComandos.add(lblPara);
		pnlComandos.add(pnlComandosBtn);
		pnlComandos.add(pnlEnvio);
		pnlPrivado.setVisible(false);
		pnlPrivado.setLayout(new BorderLayout());
		pnlPrivado.add(new JLabel("Privado"), BorderLayout.NORTH);
		pnlPrivado.add(spPrivado, BorderLayout.CENTER);
		pnlPrivado.add(pnlComandos, BorderLayout.SOUTH);
		splPanel2.setOneTouchExpandable(true);
		splPanel2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splPanel2.add(pnlEntrada, JSplitPane.TOP);
		splPanel2.add(pnlPrivado, JSplitPane.BOTTOM);
		splPanel.setOneTouchExpandable(true);
		splPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splPanel.add(spConectados, JSplitPane.LEFT);
		splPanel.add(splPanel2, JSplitPane.RIGHT);
		txtComando.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					enviarComando();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enviarComando();
			}
		});
		lblStatus.setPreferredSize(new Dimension(0, 20));
		pnlStatus.add(lblStatus);
		tpnlContenido.add("Transmsiones Locales", splPanel);
		tpnlContenido.add("Transmsiones Foraneas", pnlForaneo);
		setJMenuBar(menuBar);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(tpnlContenido, BorderLayout.CENTER);
		getContentPane().add(lblStatus, BorderLayout.SOUTH);
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
		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		// Close the window
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
	}

	void seleccionarEquipo() {
		try {
			indiceSeleccionado = lstConectados.getSelectedIndex();
			valorSeleccionado = lstConectados.getSelectedValue().toString();

			GPSServer.gpsh.get(lstConectados.getSelectedIndex()).privado = true;

			lblPara.setText("Mensaje para el GPS "
					+ lstConectados.getSelectedValue().toString() + ": ");
			pnlPrivado.setVisible(true);
			splPanel2.setDividerLocation(0.2);
			lstConectados.setEnabled(false);
			txtComando.requestFocusInWindow();
			btnActivar.setText("Desactivar");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Selecione un item de la lista.");
			btnActivar.setSelected(false);
		}
	}

	void deseleccionarEquipo() {
		try {
			GPSServer.gpsh.get(indiceSeleccionado).privado = false;
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
		}
		lblPara.setText("");
		txtComando.setText("");
		taPrivado.setText("");
		pnlPrivado.setVisible(false);
		lstConectados.setEnabled(true);
		btnActivar.setText("Activar");
		btnActivar.setSelected(false);
	}

	void enviarComando() {
		int i;

		String cmd;
		if (chkForzarMayuscula.isSelected())
			cmd = ">" + txtComando.getText().toUpperCase() + "<";
		else
			cmd = ">" + txtComando.getText() + "<";

		try {
			if (!GPSServer.gpsh.get(indiceSeleccionado).name
					.equals(valorSeleccionado))
				throw new Exception(
						"El GPS solicitado cambio de ID en la lista.");
			GPSServer.gpsh.get(indiceSeleccionado).enviarMensaje(cmd);
			taPrivado.append(cmd + "\n");
			txtComando.setText("");
		} catch (Exception e) {
			MyLogger.escribirLog(this.getClass().getName(), e.getMessage());
			boolean encontrado = false;
			for (i = 0; i < lstConectados.getModel().getSize(); i++) {
				if (lstConectados.getModel().getElementAt(i)
						.equals(valorSeleccionado)) {
					indiceSeleccionado = i;
					encontrado = true;
					break;
				}
			}
			if (!encontrado) {
				JOptionPane.showMessageDialog(null,
						"El equipo parece haberse desconectado.");
				deseleccionarEquipo();
			} else {
				lstConectados.setSelectedIndex(i);
				seleccionarEquipo();
				enviarComando();
			}
		}
	}

	public static void main(String[] args) {
		FramePrincipal f = new FramePrincipal();
		f.lstConectados.setListData(new String[] { "hola", "mundo" });
	}
}
