package org.rajnegi.rpn;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

public class LaunchRpnCalculator {

	public static void main(String[] args) {

		System.out.println("Enter expression to evaluate or 'q' to quit");
    	
    	Scanner in;
    	in = new Scanner(System.in);
    	
    	Calculator calculator = new Calculator();

        boolean flag = true;
        while (flag) {
        	
            String inputString = in.nextLine();
            
            if ("q".equalsIgnoreCase(inputString)) {
                flag = false;
            } else {
                try {
                    calculator.evaluateExpression(inputString, false);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                //Display stack values up to 10 decimal places.
                DecimalFormat formatter = new DecimalFormat("0.##########");
                Stack<Double> stack = calculator.getValues();
                System.out.print("Stack: ");
                for (Double value : stack) {
                    System.out.print(formatter.format(value));
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    	
    	in.close();
	}

}
