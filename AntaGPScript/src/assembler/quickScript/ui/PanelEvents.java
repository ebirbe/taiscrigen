package assembler.quickScript.ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PanelEvents extends JPanel {
	private static final long serialVersionUID = -3915679394116582560L;
	public PanelTimeDistance pnlTD = new PanelTimeDistance();
	public PanelLimiteVelocidad pnlLimitVelocidad = new PanelLimiteVelocidad();
	public PanelCambioPuerto pnlCambioPuerto = new PanelCambioPuerto();
	public PanelBateriaBaja pnlBateriaBaja = new PanelBateriaBaja();
	public PanelCambioDireccion pnlCambioDireccion = new PanelCambioDireccion();
	public PanelEventoSimple pnlEncendido = new PanelEventoSimple("Reportar el encendido");
	public PanelEventoSimple pnlAlimentacion = new PanelEventoSimple("Reportar desconexion de energia");
	public PanelEventoSimple pnlApagSeguro = new PanelEventoSimple("Activar apagado seguro del motor");
	
	public PanelEvents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEtchedBorder());
		add(pnlTD);
		add(pnlLimitVelocidad);
		add(pnlCambioPuerto);
		add(pnlBateriaBaja);
		add(pnlCambioDireccion);
		pnlEncendido.lblTitulo.setText("OTROS");
		add(pnlEncendido);
		pnlAlimentacion.remove(pnlAlimentacion.pnlTitulo);
		add(pnlAlimentacion);
		pnlApagSeguro.remove(pnlApagSeguro.pnlTitulo);
		add(pnlApagSeguro);
	}
}
