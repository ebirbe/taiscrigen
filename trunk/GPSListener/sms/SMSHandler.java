package sms;

import java.io.IOException;

import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.UnsupportedCommOperationException;

public class SMSHandler {
	public SMSHandler() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		//new SMSClient(0).sendMessage("04128663381", "Prueba Java!");
		try {
			SerialToGsm stg;
			stg = new SerialToGsm("/dev/ttyUSB0");
			String msg = "MENSAJE MULTIPLE SIN CHOQUES, POR FAVOR.";
			new HiloMensajero(stg, "04128663381", msg);
			new HiloMensajero(stg, "04262368741", msg);
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
