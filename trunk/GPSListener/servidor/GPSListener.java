package servidor;

import log.MyLogger;
import config.Configuracion;

public class GPSListener {
	public static Configuracion config = new Configuracion();;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyLogger.escribirLog("###################################################################");
		MyLogger.escribirLog("#######################INICIANDO GPSLISTENER#######################");
		MyLogger.escribirLog("###################################################################");
		
		if(args.length > 0){
			if(args[0].equals("--config")){
				new FrameConfServidor(config);
			}else{
				System.out.println();
				System.out.println("USO:");
				System.out.println("gpslistener [--config]");
				System.out.println("--config \tMuestra el dialogo de configuracion.");
				System.out.println();
			}
		}else{
			iniciarServidor();
		}
	}

	private static void iniciarServidor() {
		MyLogger.escribirLog("Puerto de Internet: "+config.getHostPort());
		MyLogger.escribirLog("Dispositivo SMS: "+config.getGsmDevice());
		new GPSServer(config.getHostPort(), config.getGsmDevice());
	}
}
