package org.rajnegi.rpn;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

import org.rajnegi.rpn.exception.OperationException;

public class Calculator {

	private final Stack<Double> values = new Stack<>();
    private final Stack<Command> commands = new Stack<>();
    //private int currIndex = 0;
    private static final String SPLITTER = " ";
    
    public void evaluateExpression(String expression, boolean isUndo) throws OperationException {
    	
    	List<String> input = Arrays.asList(expression.split(SPLITTER));
    	
    	int currIndex = 0;
    	for(String token : input) {
    		
    		currIndex = currIndex+2;
    		Operation opr = Operation.get(token);
    		
    		if(opr == null) {
    			//This could be a digit
    			values.push(parseDouble(token));
    			if(!isUndo) {
    				//If it's a digit push DONOTHING command
					commands.push(new Command(Operation.DONOTHING, 0.0d));
				}
    		}else {
    			
    			Double firstParameter = 0.0d,secondParameter = 0.0d;
    			if(opr != Operation.UNDO && opr != Operation.CLEAR) {
    				if(opr.getNoOfReqdOperands() > values.size()) {
        				throw new OperationException(
        		                String.format("operator %s (position: %d): insufficient parameters", opr.getOperator(), --currIndex));
        			}
        			
    				firstParameter = values.pop();
    		        secondParameter = (opr.getNoOfReqdOperands() > 1) ? values.pop() : null;
    			}
    			
    			switch(opr) {
    				case ADD: {
    					BiFunction<Double, Double, Double> addFunction = (no1, no2) ->  {
    						Double result = no1 + no2;
    						values.push(result);
    						if(!isUndo) {
    							commands.push(new Command(opr, no1));
    						}
    						return result;
    					};
    					opr.calculate( addFunction , firstParameter, secondParameter);
    					break;
    				}
    				case SUBTRACT: {
    					
    					BiFunction<Double, Double, Double> subtractFunction = (no1, no2) ->  {
    						Double result = no2 - no1;
    						values.push(result);
    						if(!isUndo) {
    							commands.push(new Command(opr, no1));
    						}
    						return result;
    					};
    					opr.calculate( subtractFunction , firstParameter, secondParameter);
    					break;
    					
    				}
    				case MULTIPLY: {
    					
    					BiFunction<Double, Double, Double> multiplyFunction = (no1, no2) ->  {
    						Double result = no2 * no1;
    						values.push(result);
    						if(!isUndo) {
    							commands.push(new Command(opr, no1));
    						}
    						return result;
    					};
    					opr.calculate( multiplyFunction , firstParameter, secondParameter);
    					break;
    					
    				}
    				case DIVIDE: {
    					
    					if(firstParameter == 0) {
    						throw new OperationException("Divide by 0 error");
    					}
    					
    					BiFunction<Double, Double, Double> divideFunction = (no1, no2) ->  {
    						Double result = no2 / no1;
    						values.push(result);
    						if(!isUndo) {
    							commands.push(new Command(opr, no1));
    						}
    						return result;
    					};
    					opr.calculate( divideFunction , firstParameter, secondParameter);
    					break;
    					
    				}
    				case SQRT: {
    					
    					BiFunction<Double, Double, Double> sqrtFunction = (no1, no2) ->  {
    						Double result = Math.sqrt(no1);
    						values.push(result);
    						if(!isUndo) {
    							commands.push(new Command(opr, no1));
    						}
    						return result;
    					};
    					opr.calculate( sqrtFunction , firstParameter, secondParameter);
    					break;
    					
    				}
    				case POWER: {
    					
    					BiFunction<Double, Double, Double> powFunction = (no1, no2) ->  {
    						Double result = Math.pow(no1, 2.0);
    						values.push(result);
    						if(!isUndo) {
    							commands.push(new Command(opr, no1));
    						}
    						return result;
    					};
    					opr.calculate( powFunction , firstParameter, secondParameter);
    					break;
    					
    					
    				}
    				case CLEAR: {
    					if(!values.isEmpty()) {
    						values.clear();
    					}
    					if(!commands.isEmpty()) {
    						commands.clear();
    					}
    					break;
    				}
    				case UNDO: {
    					
    					if(commands.isEmpty()) {
    						if(!values.isEmpty()) {
    							values.pop();
    						}
    						break;
    					}
    					
    					Command lastCommand = commands.pop();
    					if(lastCommand.operation == Operation.DONOTHING) {
    						values.pop();
    						break;
    					}else {
    						String undoCommand = lastCommand.getUndoCommand();
							evaluateExpression(undoCommand, true);
    					}
    					break;
    					
    				}
				default:
					break; 
    			}
    			
    		}
    		
    	}
    }
    
    private Double parseDouble(String token) throws OperationException {
    	try {
            return Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            throw new OperationException(
    		                String.format("Invalid token - %s", token));
        }
    }

	public Stack<Double> getValues() {
		return values;
	}
    
	public Stack<Command> getCommands() {
		return commands;
	}
}
