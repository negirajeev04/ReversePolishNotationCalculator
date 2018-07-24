package org.rajnegi.rpn.test;

import org.junit.Test;
import org.rajnegi.rpn.Calculator;
import org.rajnegi.rpn.exception.OperationException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RpnCalculatorTest {

	@Test
	public void test1() throws OperationException {
		Calculator calc = new Calculator();
		calc.evaluateExpression("5 2", false);
		assertThat(calc.getValues().size(), is(equalTo(2)));
	}
	
	@Test
	public void test2() throws OperationException {
		Calculator calc = new Calculator();
		calc.evaluateExpression("16 sqrt", false);
		assertThat(calc.getValues().peek(), is(equalTo(4.0)));
	}
	
	@Test
	public void test3() throws OperationException {
		Calculator calc = new Calculator();
		calc.evaluateExpression("5 2 -", false);
		assertThat(calc.getValues().peek(), is(equalTo(3.0)));
	}
	
	@Test
	public void test4() throws OperationException {
		Calculator calc = new Calculator();
		calc.evaluateExpression("5 4 3 2 undo undo *", false);
		assertThat(calc.getValues().peek(), is(equalTo(20.0)));
	}
	
	@Test
	public void test5() throws OperationException {
		Calculator calc = new Calculator();
		calc.evaluateExpression("5 2 - 3 -", false);
		assertThat(calc.getValues().peek(), is(equalTo(0.0)));
	}
	
	@Test
	public void test6() throws OperationException {
		Calculator calc = new Calculator();
		calc.evaluateExpression("7 12 2 /", false);
		assertThat(calc.getValues().size(), is(equalTo(2)));
		calc.evaluateExpression("*", false);
		assertThat(calc.getValues().peek(), is(equalTo(42.0)));
		calc.evaluateExpression("4 /", false);
		assertThat(calc.getValues().peek(), is(equalTo(10.5)));
	}
	
	@Test
	public void test7() throws OperationException {
		Calculator calc = new Calculator();
		calc.evaluateExpression("1 2 3 4 5", false);
		assertThat(calc.getValues().size(), is(equalTo(5)));
		calc.evaluateExpression("*", false);
		assertThat(calc.getValues().size(), is(equalTo(4)));
		calc.evaluateExpression("clear 3 4 -", false);
		assertThat(calc.getValues().peek(), is(equalTo(-1.0)));
	}
	
}
