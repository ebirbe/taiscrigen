package assembler.model;

import assembler.model.constants.LogicalOperator;

public class EventCondition {
	public String signal = new String();
	public LogicalOperator logicOperator;
	public EventCondition() {
		super();
	}
	public EventCondition(String signal) {
		super();
		this.signal = signal;
	}
	public EventCondition(String signal, LogicalOperator lo) {
		super();
		this.signal = signal;
		this.logicOperator = lo;
	}
}
