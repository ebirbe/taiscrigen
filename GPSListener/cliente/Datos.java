package cliente;

public class Datos {
	public int id_vehiculo;
	public String tlf_cliente;
	public String nota = "";
	public Datos(int id) {
		switch (id) {
		case 3201:// Jaky47
			this.id_vehiculo = id;
			this.tlf_cliente = "04124662746";
			break;
		case 3202:// Noe Hernandez
			this.id_vehiculo = id;
			this.tlf_cliente = "04243294388";
			break;
		case 3203:// Antonio Porras
			this.id_vehiculo = id;
			this.tlf_cliente = "04142564747";
			break;
		case 3204:// Transman
		case 3205:// Transman
		case 3211:// Transman
		case 3212:// Transman
			this.id_vehiculo = id;
			this.tlf_cliente = "04143441739";
			break;
		case 3206:// Luis Perera
			this.id_vehiculo = id;
			this.tlf_cliente = "04142803413";
			break;
		case 3207:// Atilio Da Silva
			this.id_vehiculo = id;
			this.tlf_cliente = "04123472766";
			break;
		case 3213:// Carlos Aviles
		case 3214:// Carlos Aviles
			this.id_vehiculo = id;
			this.tlf_cliente = "04163448451";
			break;
		case 3215:// Alexander Santana
			this.id_vehiculo = id;
			this.tlf_cliente = "04169479281";
			break;
		default:
			this.id_vehiculo = id;
			this.tlf_cliente = "04124662746";
			this.nota = "\rID SIN DESTINATARIO.";
			break;
		}
	}
}
