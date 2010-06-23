package essenger.servidor;

import java.io.IOException;

public class ServidorChat {
	public static void main(String[] args) {
		try {
			new ConectorServidor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
