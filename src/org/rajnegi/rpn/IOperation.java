package org.rajnegi.rpn;

import java.util.function.BiFunction;

import org.rajnegi.rpn.exception.OperationException;

public interface IOperation {

	public Double calculate(BiFunction<Double, Double, Double> function, Double firstOperand, Double secondOperand) throws OperationException;
	
}
