package servidor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import config.Configuracion;

public class FrameConfServidor extends JFrame {
	private static final long serialVersionUID = -4273379014515750608L;

	private Configuracion config;
	private Integer hostPort;
	private String gsmModemPort;

	public Integer getHostPort() {
		return hostPort;
	}

	public void setHostPort(Integer hostPort) {
		this.hostPort = hostPort;
	}

	public String getGsmModemPort() {
		return gsmModemPort;
	}

	public void setGsmModemPort(String gsmModemPort) {
		this.gsmModemPort = gsmModemPort;
	}

	private JTextField txtPort = new JTextField();
	private JLabel lblPort = new JLabel("Puerto de Escucha:");
	private JPanel pnlPort = new JPanel();

	private JPanel pnlGsm = new JPanel();
	private JLabel lblGsmModemPort = new JLabel("Puerto de SMS:");
	private JTextField txtGsmDevice = new JTextField();

	private JButton btnAceptar = new JButton("Aceptar");
	private JButton btnSalir = new JButton("Salir");
	private JPanel pnlBotones = new JPanel();

	public FrameConfServidor(Configuracion cnf) {

		this.config = cnf;
		hostPort = cnf.getHostPort();
		gsmModemPort = cnf.getGsmDevice();

		txtPort.setText(hostPort.toString());
		txtPort.setPreferredSize(new Dimension(50, 25));
		pnlPort.add(lblPort);
		pnlPort.add(txtPort);
		txtGsmDevice.setText(gsmModemPort);
		txtGsmDevice.setPreferredSize(new Dimension(150, 25));
		pnlGsm.add(lblGsmModemPort);
		pnlGsm.add(txtGsmDevice);
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Coprobamos que sea un entero
					Integer.parseInt(txtPort.getText());
					config.put(Configuracion.keyHostPort, txtPort.getText());
					config.put(Configuracion.keyGsmDevice,
							txtGsmDevice.getText());
					config.guardarConfiguracion();
					JOptionPane
							.showMessageDialog(
									FrameConfServidor.this,
									"Configuracion guardada. Sera valida la proxima vez que inicie el programa.",
									"Configuracion Guardada",
									JOptionPane.PLAIN_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(
							servidor.FrameConfServidor.this,
							"Indique un puerto de host valido.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				setGsmModemPort(txtGsmDevice.getText());
				WindowEvent ev = new WindowEvent(FrameConfServidor.this,
						WindowEvent.WINDOW_CLOSED);
				FrameConfServidor.this.processWindowEvent(ev);
			}
		});
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processWindowEvent(new WindowEvent(FrameConfServidor.this,
						WindowEvent.WINDOW_CLOSING));
			}
		});
		pnlBotones.setLayout(new GridBagLayout());
		pnlBotones.add(btnAceptar);
		pnlBotones.add(btnSalir);
		setLayout(new FlowLayout());
		setTitle("Configuracion de Arranque");
		add(pnlPort);
		add(pnlGsm);
		add(pnlBotones);
		pack();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		setVisible(true);
	}
}
