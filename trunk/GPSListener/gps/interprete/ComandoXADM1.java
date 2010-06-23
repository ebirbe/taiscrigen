package gps.interprete;

public class ComandoXADM1 extends Comando {

	private String nivel, fwFamily, fwVersion, fwVersionTipo, estadoSim, estadoGsm, rssi, gprsAttach, estadoGprs, numeroSatelite, fuente, edad, estadoTracking, gpiosMask, ioState, resetDiagnostic, dpActual, keepAlive;

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getFwFamily() {
		return fwFamily;
	}

	public void setFwFamily(String fwFamily) {
		this.fwFamily = fwFamily;
	}

	public String getFwVersion() {
		return fwVersion;
	}

	public void setFwVersion(String fwVersion) {
		this.fwVersion = fwVersion;
	}

	public String getFwVersionTipo() {
		return fwVersionTipo;
	}

	public void setFwVersionTipo(String fwVersionTipo) {
		this.fwVersionTipo = fwVersionTipo;
	}

	public String getEstadoSim() {
		return estadoSim;
	}

	public void setEstadoSim(String estadoSim) {
		this.estadoSim = estadoSim;
	}

	public String getEstadoGsm() {
		return estadoGsm;
	}

	public void setEstadoGsm(String estadoGsm) {
		this.estadoGsm = estadoGsm;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	public String getGprsAttach() {
		return gprsAttach;
	}

	public void setGprsAttach(String gprsAttach) {
		this.gprsAttach = gprsAttach;
	}

	public String getEstadoGprs() {
		return estadoGprs;
	}

	public void setEstadoGprs(String estadoGprs) {
		this.estadoGprs = estadoGprs;
	}

	public String getNumeroSatelite() {
		return numeroSatelite;
	}

	public void setNumeroSatelite(String numeroSatelite) {
		this.numeroSatelite = numeroSatelite;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getEstadoTracking() {
		return estadoTracking;
	}

	public void setEstadoTracking(String estadoTracking) {
		this.estadoTracking = estadoTracking;
	}

	public String getGpiosMask() {
		return gpiosMask;
	}

	public void setGpiosMask(String gpiosMask) {
		this.gpiosMask = gpiosMask;
	}

	public String getIoState() {
		return ioState;
	}

	public void setIoState(String ioState) {
		this.ioState = ioState;
	}

	public String getResetDiagnostic() {
		return resetDiagnostic;
	}

	public void setResetDiagnostic(String resetDiagnostic) {
		this.resetDiagnostic = resetDiagnostic;
	}

	public String getDpActual() {
		return dpActual;
	}

	public void setDpActual(String dpActual) {
		this.dpActual = dpActual;
	}

	public String getKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(String keepAlive) {
		this.keepAlive = keepAlive;
	}

	public ComandoXADM1(String texto) {
		super(texto);
	}

	@Override
	public void quitarPrevios() {
		// Eliminamos REV
		texto = texto.substring(5);
	}

	@Override
	public void desglosarComando() {
		// A BB CC DD E F GG H I JJ K L M NN OO PP QQ RRR
		//System.out.println(texto);
		if(texto.indexOf(";") > -1) texto = texto.substring(0,texto.indexOf(";"));
		
		nivel = texto.substring(0,1);
		fwFamily = texto.substring(1,3);
		fwVersion = texto.substring(3, 5);
		fwVersionTipo = texto.substring(5,7);
		estadoSim = texto.substring(7,8);
		estadoGsm = texto.substring(8,9);
		rssi = texto.substring(9,11);
		gprsAttach = texto.substring(11,12);
		estadoGprs = texto.substring(12,13);
		numeroSatelite = texto.substring(13,15);
		fuente = texto.substring(15,16);
		edad = texto.substring(16,17);
		estadoTracking = texto.substring(17,18);
		gpiosMask = texto.substring(18,20);
		ioState = texto.substring(20,22);
		resetDiagnostic = texto.substring(22,24);
		if(texto.length() == 29) {
			dpActual = texto.substring(24,26);
			keepAlive = texto.substring(26,29);
		}else{
			dpActual = "0"+texto.substring(24,25);
			keepAlive = texto.substring(25,28);
		}
	}

	@Override
	public String imprimir() {

		impresion ="";
		impresion += "Nivel Diagnostico: "+nivel+"\n";
		impresion += "Firmware: "+fwFamily+"."+fwVersion+" ("+fwVersionTipo+")\n";
		impresion += "Estado de SIM: "+obtenerEstadoSim(estadoSim)+"\n";
		impresion += "Estado GSM: "+obtenerEstadoGsm(estadoGsm)+"\n";
		impresion += "Fuerza de Señal (RSSI): "+Integer.parseInt(rssi)+"/31\n";
		impresion += "GPRS unido: "+obtenerGprsAttached(gprsAttach)+"\n";
		impresion += "Estado GPRS: "+obtenerEstadoGprs(estadoGprs)+"\n";
		impresion += "Numero Satelites: "+Integer.parseInt(numeroSatelite)+"\n";
		impresion += "Fuente: "+obtenerFuente(fuente)+"\n";
		impresion += "Edad: "+obtenerEdad(edad)+"\n";
		impresion += "Estado de Rastreo: "+obtenerEstadoRastreo(estadoTracking)+"\n";
		impresion += "Mascara GPIOS: "+gpiosMask+"\n";
		impresion += "Estado de E/S: "+ioState+"\n";
		impresion += "Dest. Diag. de Reset: "+obtenerDiagnosticoReset(resetDiagnostic)+"\n";
		impresion += "DP actual: DP"+dpActual+"\n";
		impresion += "Tiempo de KeepAlive: Cada "+Integer.parseInt(keepAlive)+" minutos.\n";

		return impresion;
	}

	protected String obtenerDiagnosticoReset(String resetDiagnostic2) {
		if(resetDiagnostic2.equals("UU")) return "Inactivo.";
		if( ! resetDiagnostic2.substring(0,1).equals("A")) resetDiagnostic2 = "P"+resetDiagnostic2;
		return "D"+resetDiagnostic2;
	}

	protected String obtenerEstadoGprs(String estadoGprs2) {
		String st = "";

		if(estadoGprs2.equals("E")) return "ERROR. (Use QXANS)";

		int iSt = Integer.parseInt(estadoGprs2);
		switch (iSt) {
		case 0:
			st = "Inicializando.";
			break;
		case 2:
			st = "Ocupado.";
			break;
		case 4:
			st = "Llamando.";
			break;
		case 6:
			st = "Conectado (Listo).";
			break;
		case 7:
			st = "Sin APN.";
			break;
		case 8:
			st = "Detenido.";
			break;
		case 9:
			st = "No hay red.";
			break;
		default:
			st = "("+estadoGprs2 + ") Desconocido.";
			break;
		}
		return st;
	}

	protected String obtenerGprsAttached(String gprsAttach2) {
		String st = "";
		int iSt = Integer.parseInt(gprsAttach2);
		switch (iSt) {
		case 1:
			st = "Si.";
			break;
		case 0:
			st = "No.";
			break;
		}
		return st;
	}

	protected String obtenerEstadoGsm(String estadoGsm2) {
		String st = "";
		int iSt = Integer.parseInt(estadoGsm2);
		switch (iSt) {
		case 0:
			st = "Sin registrar, sin buscar.";
			break;
		case 1:
			st = "Registrado, red local.";
			break;
		case 2:
			st = "No registrado, buscando.";
			break;
		case 3:
			st = "Registro rechazado.";
			break;
		case 4:
			st = "Desconocido.";
			break;
		case 5:
			st = "Registrado, en roaming.";
			break;
		}
		return st;
	}

	protected String obtenerEstadoSim(String estadoSim2) throws NumberFormatException{
		String st = "";
		int iSt = Integer.parseInt(estadoSim2);
		switch (iSt) {
		case 0:
			st = "No quedan intentos de PIN.";
			break;
		case 3:
			st = "Error de PIN.";
			break;
		case 6:
			st = "SIM lista.";
			break;
		case 7:
			st = "No se ha introducido PIN.";
			break;
		case 9:
			st = "Iniciando.";
			break;
		default:
			break;
		}
		return st;
	}
}
