package org.rajnegi.rpn;

import java.util.function.BiFunction;

public enum Operation implements IOperation{
	ADD ("+", "-", 2),
	SUBTRACT ("-", "+", 2),
	MULTIPLY ("*", "/", 2),
	DIVIDE ("/", "*", 2),
	SQRT ("sqrt", "pow", 1),
	POWER("pow", "sqrt", 1),
	UNDO ("undo", null , 0),
	CLEAR ("clear", null, 0),
	DONOTHING("donothing", null, 0);

	@Override
	public Double calculate(BiFunction<Double, Double, Double> function, Double firstOperand, Double secondOperand) {
		return function.apply(firstOperand, secondOperand);
	}

	private final String operator;
	private final String reverseOperator;
	private final int noOfReqdOperands;
	
	public String getOperator() {
		return operator;
	}

	public String getReverseOperator() {
		return reverseOperator;
	}

	public int getNoOfReqdOperands() {
		return noOfReqdOperands;
	}

	Operation(String operator, String reverseOperator, int noOfReqdOperands){
		this.operator = operator;
		this.reverseOperator = reverseOperator;
		this.noOfReqdOperands = noOfReqdOperands;
	}
	
	public static Operation get(String symbol) {
		for(Operation opr : values()) {
			if(symbol.equals(opr.getOperator())) {
				return opr;
			}
		}
		return null;
	}
}
