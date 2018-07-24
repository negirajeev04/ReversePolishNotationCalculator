package org.rajnegi.rpn;

public class Command {
    Operation operation;
    Double firstOperand;

    public Command(Operation operation, Double value) {
        this.operation = operation;
        this.firstOperand = value;
    }

    public String getUndoCommand() {

    	if(operation.getNoOfReqdOperands() < 2) {
    		return operation.getOperator();
    	}else {
    		return String.format("%f %s %f", firstOperand, operation.getReverseOperator(), firstOperand);
    	}
    }

	@Override
	public String toString() {
		return "Command [operation=" + operation + ", firstOperand=" + firstOperand + "]";
	}
    
}
