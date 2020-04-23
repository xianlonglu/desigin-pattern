package com.gupaoedu.vip.pattern.interpreter.calculate1;

public enum OperatorEnum {

	LEFT_BRACKET("("), RIGHT_BRACKET(")"), SUB("-"), ADD("+"), MULTI("*"), DIV("/"), ;

	private String operator;

	public String getOperator() {
		return operator;
	}

	OperatorEnum(String operator) {
		this.operator = operator;
	}
}
