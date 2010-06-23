package assembler.model.constants;

public enum EventSense {
	UNDEFINED	(""),
	RAISING		("+"),
	FALLING		("+");
	private final String x;
	private EventSense(String x) {
		this.x = x;
	}
	@Override
	public String toString() {
		return x;
	}
}
