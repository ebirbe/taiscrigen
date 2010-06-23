package sms;

public class SMSHandler {
	public SMSHandler() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		//new SMSClient(0).sendMessage("04128663381", "Prueba Java!");
		new SerialToGsm(args[0]).sendSms("04128663381", "HOLA :) JAVA STA SIENDO USADO CON LA LIBRERIA javax.comm PARA ENVIAR STE MSJ.");
	}
}
