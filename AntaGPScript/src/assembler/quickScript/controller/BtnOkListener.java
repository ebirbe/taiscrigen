package assembler.quickScript.controller;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import data.TempBuffer;

import assembler.controller.OkListener;
import assembler.model.EventAction;
import assembler.model.EventCondition;
import assembler.model.Sender;
import assembler.model.constants.BatteryActiveFlag;
import assembler.model.constants.EventHandling;
import assembler.model.constants.EventSense;
import assembler.model.constants.LogicalOperator;
import assembler.model.constants.MessageID;
import assembler.quickScript.ui.MainUI;
import assembler.quickScript.ui.PanelDestinationAdress;
import assembler.quickScript.ui.PanelSubEvent;

public class BtnOkListener extends OkListener {

	MainUI ui;
	Sender snd;

	public BtnOkListener(MainUI ui) {
		super(ui);
		this.ui = ui;
		snd = new Sender();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Validaciones
		try {
			validar();
		} catch (ExcepcionDeValidacion e1) {
			JOptionPane.showMessageDialog(ui,e1.getTexto());
			return;
		}
		// Resetear Configuracion
		snd.mkReset(this.ui.chkResetConfig.isSelected());
		// ID del equipo
		snd.mkID(ui.txtID.getText(), ui.chkIMEIasID.isSelected());
		// Access Point
		snd.mkAPN(ui.txtApn.getText());
		// cdigo PIN de la tarjeta
		if(!ui.txtPIN.getText().equals("")) snd.mkPIN(ui.txtPIN.getText());
		// Destination Points
		destinationPoints();
		// Destination Adresses
		destinationAdresses();
		// Time and Distance Event
		timeAndDistance();
		// Speed Limit
		speedLimit();
		// Cambio de puerto
		portChange();
		// Bateria Baja
		batteryLow();
		// Cambio de Direccion
		directionChange();
		// Encendido
		ignition();
		
		// Send the data generated to the buffer
		snd.SendToBuffer();

		for (String bf : TempBuffer.buffer) {
			System.out.print(bf);
		}

		super.actionPerformed(e);
	}
	

	private void ignition() {
		if(!ui.pnlEvt.pnlEncendido.chkActivar.isSelected()) return;
		
	}

	private void directionChange() {
		if(!ui.pnlEvt.pnlCambioDireccion.chkActivar.isSelected()) return;
		// Heading Delta
		snd.mkHeadingDelta(Integer.parseInt(ui.pnlEvt.pnlCambioDireccion.txtID.getText()), "1", Integer.parseInt(ui.pnlEvt.pnlCambioDireccion.txtGrados.getText()));
		// Evento
		
		Vector<EventCondition> ec = new Vector<EventCondition>();
		EventCondition a = new EventCondition();
		a.signal = "J"+Sender.intFiller(Integer.parseInt(ui.pnlEvt.pnlCambioDireccion.txtID.getText()), 2);
		a.logicOperator = LogicalOperator.UNDEFINED;
		ec.add(a);
		
		EventSense es = EventSense.RAISING;
		
		snd.mkEventDefinition(
				Integer.parseInt(ui.pnlEvt.pnlCambioDireccion.txtEvtID.getText()),
				EventHandling.N,
				MessageID.V,
				Integer.parseInt(ui.pnlEvt.pnlCambioDireccion.cbxDA.getSelectedItem().toString()),
				ec,
				es,
				new Vector<EventAction>()
		);
	}

	private void batteryLow() {
		if(!ui.pnlEvt.pnlBateriaBaja.chkActivar.isSelected()) return;
		// Bateria baja
		BatteryActiveFlag baf = BatteryActiveFlag.P;
		switch (ui.pnlEvt.pnlBateriaBaja.cbxUnidad.getSelectedIndex()) {
		case 0:
			baf = BatteryActiveFlag.P;
			break;
		case 1:
			baf = BatteryActiveFlag.V;
			break;
		}
		snd.mkBatteryLevel(
				Integer.parseInt(ui.pnlEvt.pnlBateriaBaja.txtID.getText()),
				baf,
				Integer.parseInt(ui.pnlEvt.pnlBateriaBaja.txtCantidad.getText())
		);
		
		// Evento
		Vector<EventCondition> ec = new Vector<EventCondition>();
		EventCondition a = new EventCondition();
		a.signal = "B"+Sender.intFiller(Integer.parseInt(ui.pnlEvt.pnlBateriaBaja.txtID.getText()), 2);
		a.logicOperator = LogicalOperator.UNDEFINED;
		ec.add(a);
		
		EventSense es = EventSense.RAISING;
		
		snd.mkEventDefinition(
				Integer.parseInt(ui.pnlEvt.pnlBateriaBaja.txtEvtID.getText()),
				EventHandling.N,
				MessageID.V,
				Integer.parseInt(ui.pnlEvt.pnlBateriaBaja.cbxDA.getSelectedItem().toString()),
				ec,
				es,
				new Vector<EventAction>()
		);
	}

	private void portChange() {
		if(!ui.pnlEvt.pnlCambioPuerto.chkActivar.isSelected()) return;
		// Creamos el evento
		Vector<EventCondition> ec = new Vector<EventCondition>();
		EventCondition a = new EventCondition();
		EventSense es = EventSense.RAISING;
		
		a.signal = "XP"+ui.pnlEvt.pnlCambioPuerto.cbxPuerto.getSelectedItem();
		a.logicOperator = LogicalOperator.UNDEFINED;
		ec.add(a);
		switch (ui.pnlEvt.pnlCambioPuerto.cbxCondicion.getSelectedIndex()) {
		case 0:
			es = EventSense.FALLING;
			break;
		case 1:
			es = EventSense.RAISING;
			break;
		}
		snd.mkEventDefinition(
				Integer.parseInt(ui.pnlEvt.pnlCambioPuerto.txtEvtID.getText()),
				EventHandling.N,
				MessageID.V,
				Integer.parseInt(ui.pnlEvt.pnlCambioPuerto.cbxDA.getSelectedItem().toString()),
				ec,
				es,
				new Vector<EventAction>()
		);
	}

	private void speedLimit() {
		// Si esta activo el evento
		if(!ui.pnlEvt.pnlLimitVelocidad.chkActivar.isSelected()) return;
		// Creamos el limite de velocidad
		snd.mkSpeedLimit(Integer.parseInt(ui.pnlEvt.pnlLimitVelocidad.txtID.getText()), true, Integer.parseInt(ui.pnlEvt.pnlLimitVelocidad.txtLimitVelocidad.getText()));
		// Creamos el evento
		Vector<EventCondition> ec = new Vector<EventCondition>();
		EventCondition a = new EventCondition();
		a.signal = "S"+Sender.intFiller(Integer.parseInt(ui.pnlEvt.pnlLimitVelocidad.txtID.getText()), 2);
		a.logicOperator = LogicalOperator.UNDEFINED;
		ec.add(a);
		snd.mkEventDefinition(
				Integer.parseInt(ui.pnlEvt.pnlLimitVelocidad.txtEvtID.getText()),
				EventHandling.N,
				MessageID.V,
				Integer.parseInt(ui.pnlEvt.pnlLimitVelocidad.cbxDA.getSelectedItem().toString()),
				ec,
				EventSense.RAISING,
				new Vector<EventAction>()
		);
	}
	
	private void timeAndDistance(){
		// Si esta activo el evento
		if(!ui.pnlEvt.pnlTD.chkActivar.isSelected()) return;
		// Creamos el disparador
		snd.mkTimeDistance(
				Integer.parseInt(ui.pnlEvt.pnlTD.txtID.getText()),
				Integer.parseInt(ui.pnlEvt.pnlTD.txtMinTime.getText()),
				Integer.parseInt(ui.pnlEvt.pnlTD.txtDistance.getText()),
				Integer.parseInt(ui.pnlEvt.pnlTD.txtMaxTime.getText())
		);
		// Evento
		Vector<EventCondition> ec = new Vector<EventCondition>();
		EventCondition a = new EventCondition();
		a.signal = "TD"+Sender.intFiller(Integer.parseInt(ui.pnlEvt.pnlTD.txtID.getText()), 2);
		a.logicOperator = LogicalOperator.UNDEFINED;
		ec.add(a);
		snd.mkEventDefinition(
				Integer.parseInt(ui.pnlEvt.pnlTD.txtEvtID.getText()),
				EventHandling.N,
				MessageID.V,
				Integer.parseInt(ui.pnlEvt.pnlTD.cbxDA.getSelectedItem().toString()),
				ec,
				EventSense.RAISING,
				new Vector<EventAction>()
		);
	}
	
	private void destinationAdresses() {
		if(ui.pnlDA1.activo)
			snd.mkDestinationAdress(Integer.parseInt(ui.pnlDA1.txtID.getText()), generarVectorDA(ui.pnlDA1));
		if(ui.pnlDA2.activo)
			snd.mkDestinationAdress(Integer.parseInt(ui.pnlDA2.txtID.getText()), generarVectorDA(ui.pnlDA2));
		if(ui.pnlDA3.activo)
			snd.mkDestinationAdress(Integer.parseInt(ui.pnlDA3.txtID.getText()), generarVectorDA(ui.pnlDA3));
		if(ui.pnlDA4.activo)
			snd.mkDestinationAdress(Integer.parseInt(ui.pnlDA4.txtID.getText()), generarVectorDA(ui.pnlDA4));
	}
	
	private Vector<String> generarVectorDA(PanelDestinationAdress pnlDa){
		Vector<String> vs = new Vector<String>();
		for(JCheckBox chk : pnlDa.chk ){
			if(chk.isSelected()) vs.add(chk.getText().substring(0, 2));
		}
		return vs;
	}
	
	private void destinationPoints(){
		// Destinations Points Internet
		dpUrl(ui.txtDPinternet1.getText(), ui.txtDPport1.getText(), ui.txtDPi1.getText());
		dpUrl(ui.txtDPinternet2.getText(), ui.txtDPport2.getText(), ui.txtDPi2.getText());
		dpUrl(ui.txtDPinternet3.getText(), ui.txtDPport3.getText(), ui.txtDPi3.getText());
		// Destinations Points Phones
		dpPhone(ui.txtDPphone1.getText(), ui.txtDPp1.getText());
		dpPhone(ui.txtDPphone2.getText(), ui.txtDPp2.getText());
		dpPhone(ui.txtDPphone3.getText(), ui.txtDPp3.getText());
		dpPhone(ui.txtDPphone4.getText(), ui.txtDPp4.getText());
		dpPhone(ui.txtDPphone5.getText(), ui.txtDPp5.getText());
	}
	
	private void dpPhone(String phone, String id){
		if(phone.equals("") || id.equals("")) return;
		snd.mkDestinationPointPhone(phone, Integer.parseInt(id), '1', '0');
	}
	
	private void dpUrl(String url,String port, String id){
		if(url.equals("") || port.equals("") ||id.equals("")) return;
		snd.mkDestinationPointIP(url, port, Integer.parseInt(id), '0', '0');
	}

	private void validar() throws ExcepcionDeValidacion {
		validarID();
		validarAPN();
		validarPIN();
		validarEventoTD();
		validarEventoLimiteVelocidad();
		validarEventoCambioPuerto();
		validarEventoBateriaBaja();
		validarEventoCambioDireccion();
		validarPanelSubEvent(ui.pnlEvt.pnlEncendido);
		validarPanelSubEvent(ui.pnlEvt.pnlAlimentacion);
		validarPanelSubEvent(ui.pnlEvt.pnlApagSeguro);


	}

	private void validarEventoCambioDireccion() throws ExcepcionDeValidacion {
		if(ui.pnlEvt.pnlCambioDireccion.chkActivar.isSelected()){
			validarPanelSubEvent(ui.pnlEvt.pnlCambioDireccion);
			
			if(ui.pnlEvt.pnlCambioDireccion.txtID.getText().equals(""))
				throw new ExcepcionDeValidacion("Indique un valor", ui.pnlEvt.pnlCambioDireccion.txtID);
			
			if(
					ui.pnlEvt.pnlCambioDireccion.txtGrados.getText().equals("")
					||
					(
							Integer.parseInt(ui.pnlEvt.pnlCambioDireccion.txtGrados.getText()) < 5
							||
							Integer.parseInt(ui.pnlEvt.pnlCambioDireccion.txtGrados.getText()) > 90
					)
			){
				throw new ExcepcionDeValidacion("El valor debe ser entre 5 y 90", ui.pnlEvt.pnlLimitVelocidad.txtLimitVelocidad);
			}
		}
	}

	private void validarEventoBateriaBaja() throws ExcepcionDeValidacion {
		if(ui.pnlEvt.pnlBateriaBaja.chkActivar.isSelected()){
			validarPanelSubEvent(ui.pnlEvt.pnlBateriaBaja);
			if(ui.pnlEvt.pnlBateriaBaja.txtID.getText().equals(""))
				throw new ExcepcionDeValidacion("Indique un valor", ui.pnlEvt.pnlBateriaBaja.txtID);
			if(ui.pnlEvt.pnlBateriaBaja.txtCantidad.getText().equals("")){
				throw new ExcepcionDeValidacion("Indique el valor minimo de bateria", ui.pnlEvt.pnlBateriaBaja.txtCantidad);
			}
			// Cuando se trata de porcentajes
			if(
					ui.pnlEvt.pnlBateriaBaja.cbxUnidad.getSelectedItem().equals("%")
					&&
					(
							Integer.parseInt(ui.pnlEvt.pnlBateriaBaja.txtCantidad.getText()) <= 0
							||
							Integer.parseInt(ui.pnlEvt.pnlBateriaBaja.txtCantidad.getText()) > 100
					)
			){
				throw new ExcepcionDeValidacion("El valor debe ser entre 0% y 100%", ui.pnlEvt.pnlBateriaBaja.txtCantidad);
			}
			// Cuando se trata de miliVoltios
			if(
					ui.pnlEvt.pnlBateriaBaja.cbxUnidad.getSelectedItem().equals("mV")
					&&
					(
							Integer.parseInt(ui.pnlEvt.pnlBateriaBaja.txtCantidad.getText()) <= 0
							||
							Integer.parseInt(ui.pnlEvt.pnlBateriaBaja.txtCantidad.getText()) > 99999
					)
			){
				throw new ExcepcionDeValidacion("El valor debe ser entre 0 mV y 99999 mV", ui.pnlEvt.pnlBateriaBaja.txtCantidad);
			}
		}		
	}

	private void validarEventoCambioPuerto() throws ExcepcionDeValidacion {
		if(ui.pnlEvt.pnlCambioPuerto.chkActivar.isSelected()){
			validarPanelSubEvent(ui.pnlEvt.pnlCambioPuerto);
		}
	}

	private void validarEventoLimiteVelocidad() throws ExcepcionDeValidacion {
		if(ui.pnlEvt.pnlLimitVelocidad.chkActivar.isSelected()){
			validarPanelSubEvent(ui.pnlEvt.pnlLimitVelocidad);
			if(ui.pnlEvt.pnlLimitVelocidad.txtID.getText().equals(""))
				throw new ExcepcionDeValidacion("Indique un valor", ui.pnlEvt.pnlLimitVelocidad.txtID);
			if(
					ui.pnlEvt.pnlLimitVelocidad.txtLimitVelocidad.getText().equals("")
					||
					(
							Integer.parseInt(ui.pnlEvt.pnlLimitVelocidad.txtLimitVelocidad.getText()) <= 0
							||
							Integer.parseInt(ui.pnlEvt.pnlLimitVelocidad.txtLimitVelocidad.getText()) > 9999
					)
			){
				throw new ExcepcionDeValidacion("El valor debe ser entre 1 y 9999", ui.pnlEvt.pnlLimitVelocidad.txtLimitVelocidad);
			}
		}

	}

	private void validarEventoTD() throws ExcepcionDeValidacion {
		if(ui.pnlEvt.pnlTD.chkActivar.isSelected()){
			validarPanelSubEvent(ui.pnlEvt.pnlTD);
			if(ui.pnlEvt.pnlTD.txtID.getText().equals(""))
				throw new ExcepcionDeValidacion("Indique un valor", ui.pnlEvt.pnlTD.txtID);
			if(
					ui.pnlEvt.pnlTD.txtMinTime.getText().equals("")
					||
					(
							Integer.parseInt(ui.pnlEvt.pnlTD.txtMinTime.getText()) <= 0
							||
							Integer.parseInt(ui.pnlEvt.pnlTD.txtMinTime.getText()) > 9999
					)
			){
				throw new ExcepcionDeValidacion("El valor debe ser entre 1 y 9999", ui.pnlEvt.pnlTD.txtMinTime);
			}
		}
	}

	private void validarPanelSubEvent(PanelSubEvent pnl) throws ExcepcionDeValidacion {
		if(pnl.chkActivar.isSelected()){
			if(pnl.cbxDA.getSelectedItem().equals("")){
				throw new ExcepcionDeValidacion("Debe seleccionar un destino", pnl.cbxDA);
			}
			if(pnl.txtEvtID.getText().equals("")){
				throw new ExcepcionDeValidacion("El ID del evento no puede estar vacio", pnl.txtEvtID);
			}
		}
	}

	private void validarPIN() throws ExcepcionDeValidacion{
		if((ui.txtPIN.getText().length() > 0 && ui.txtPIN.getText().length() < 4) || ui.txtPIN.getText().length() > 8){
			throw new ExcepcionDeValidacion("El codigo PIN debe ser de 4 a 8 caracteres.\nDeje este campo en blanco si no desea configurar el PIN", ui.txtPIN);
		}
	}

	private void validarAPN() throws ExcepcionDeValidacion{
		if(ui.txtApn.getText().length() <= 0){
			throw new ExcepcionDeValidacion("Debe indicar un Acces Point", ui.txtApn);
		}
	}

	private void validarID() throws ExcepcionDeValidacion {
		if(ui.txtID.isEnabled() && ui.txtID.getText().length() <= 0){
			throw new ExcepcionDeValidacion("El ID del equipo no puede estar vacio", ui.txtID);
		}
	}
}