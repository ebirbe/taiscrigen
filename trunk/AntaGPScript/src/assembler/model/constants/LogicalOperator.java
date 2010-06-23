package assembler.model.constants;

public enum LogicalOperator {
	UNDEFINED	(""),
	AND 		("&"),
	OR 			("|"),
	NOT			("!");
	private final String x;
	private LogicalOperator(String x) {
		this.x = x;
	}
	@Override
	public String toString() {
		return x;
	}
}
