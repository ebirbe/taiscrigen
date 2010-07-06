package servidor;

import chat.ClienteForaneo;
import log.MyLogger;
import config.Configuracion;

public class GPSListener {
	public static Configuracion config = new Configuracion();;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyLogger.escribirLog(GPSListener.class.getName(),"###################################################################");
		MyLogger.escribirLog(GPSListener.class.getName(),"#######################INICIANDO GPSLISTENER#######################");
		MyLogger.escribirLog(GPSListener.class.getName(),"###################################################################");
		
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
		MyLogger.escribirLog(GPSListener.class.getName(),"Puerto de Internet: "+config.getHostPort());
		MyLogger.escribirLog(GPSListener.class.getName(),"Dispositivo SMS: "+config.getGsmDevice());
		new GPSServer(config.getHostPort(), config.getGsmDevice());
		new ClienteForaneo(GPSServer.wp.pnlForaneo);
	}
}
