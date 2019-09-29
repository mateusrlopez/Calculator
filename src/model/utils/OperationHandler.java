package model.utils;

import java.awt.Toolkit;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.scene.control.Label;

public class OperationHandler {

	public static boolean operationInProgress;
	public static boolean lockInput;
	public static boolean specialOperationInProgress;
	private static String result;
	private static String temporaryResult;
	
	public static void handleOperations(String op, Label lb1, Label lb2, Label lb3) throws ScriptException {
		String currentOperand = lb1.getText();
		char lastOperand;

		if (currentOperand.matches("\\d*[\\.]")) {
			currentOperand = Constants.BCKSP_FUNC.apply(currentOperand);
			lb1.setText(currentOperand);
		}
		try {
			if (op.equals("Reciproc")) {
				if(!specialOperationInProgress) temporaryResult = processOperation("1/"+lb1.getText());
				else temporaryResult = processOperation("1/"+temporaryResult);
				handleSpecialOperation(lb1, lb2, "reciproc");
			} else if (op.equals("√")) {
				if(!specialOperationInProgress) temporaryResult = processOperation(String.format("Math.sqrt(%s)",lb1.getText()));
				else temporaryResult = processOperation(String.format("Math.sqrt(%s)",temporaryResult));
				handleSpecialOperation(lb1, lb2, "sqrt");
			} else if (op.equals("x²")) {
				if(!specialOperationInProgress) temporaryResult = processOperation(String.format("Math.pow(%s,2)",lb1.getText()));
				else temporaryResult = processOperation(String.format("Math.pow(%s,2)",temporaryResult));
				handleSpecialOperation(lb1, lb2, "square");
			} else if (op.equals("x³")) {
				if(!specialOperationInProgress) temporaryResult = processOperation(String.format("Math.pow(%s,3)",lb1.getText()));
				else temporaryResult = processOperation(String.format("Math.pow(%s,3)",temporaryResult));
				handleSpecialOperation(lb1, lb2, "cube");
			} else {
				if (operationInProgress && InputHandler.isOverridible && !specialOperationInProgress)
					lb2.setText(lb2.getText().substring(0, lb2.getText().length() - 1) + op);
				else if (specialOperationInProgress && !operationInProgress) {
					lb2.setText(lb2.getText() + " " + op);
					result = temporaryResult;
					specialOperationInProgress = false;
					operationInProgress = true;
				} else if (specialOperationInProgress && operationInProgress) {
					lastOperand = lb2.getText().split(" ")[lb2.getText().split(" ").length-2].charAt(0);
					lb2.setText(lb2.getText() + String.format(" %s",op));
					result = OperationHandler.processOperation(result + lastOperand + temporaryResult);
					lb1.setText(String.format("%s",result));
					InputHandler.isOverridible = true;
					specialOperationInProgress = false;
				} else if (operationInProgress && !InputHandler.isOverridible) {
					lastOperand = lb2.getText().charAt(lb2.getText().length() - 1);
					lb2.setText(lb2.getText() + String.format(" %s %s", currentOperand, op));
					result = OperationHandler.processOperation(result + lastOperand + currentOperand);
					lb1.setText(String.format("%s",result));
					InputHandler.isOverridible = true;
				} else {
					lb2.setText(lb2.getText() + String.format(" %s %s", currentOperand, op));
					result = currentOperand;
					operationInProgress = true;
					InputHandler.isOverridible = true;
				}
			}
		} catch (OperationException e) {
			Toolkit.getDefaultToolkit().beep();
			lb1.setText("");
			lb3.setText(e.getMessage());
			lockInput = true;
		}
	}
	
	public static void handleEquals(Label lb1, Label lb2, Label lb3) throws ScriptException {
		if (lb2.getText().equals("")) 
			InputHandler.isOverridible = true;
		else {
			try {
				if (operationInProgress && !specialOperationInProgress)
					result = processOperation(result + lb2.getText().charAt(lb2.getText().length() - 1) + lb1.getText()).toString();
				else if (specialOperationInProgress && !operationInProgress)
					result = temporaryResult;
				else {
					char lastOperand = lb2.getText().split(" ")[lb2.getText().split(" ").length-2].charAt(0);
					result = processOperation(result + lastOperand + temporaryResult).toString();
				}
				lb2.setText("");
				lb1.setText(String.format("%s",result));
				InputHandler.isOverridible = true;
				operationInProgress = false;
				specialOperationInProgress = false;
			} catch (OperationException e) {
				Toolkit.getDefaultToolkit().beep();
				lb1.setText("");
				lb3.setText(e.getMessage());
				lockInput = true;
			}
		}
	}

	private static String processOperation(String operation) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		Object result = engine.eval(operation);
		System.out.println(result);
		if (!result.toString().matches(Constants.REGEX1))
			throw new OperationException("Operação inválida");
		return handleResultString(result.toString());
	}
	
	private static String handleResultString(String result) {
		if(result.length() > 20) {
			if(result.matches("(\\-)?\\d*[\\.]\\d*[E](\\-)?\\d*?")) {
				int i = result.indexOf("E");
				String exponentialPart = result.substring(i);
				result = result.substring(0,20-exponentialPart.length()) + exponentialPart;
			}
			else result = result.substring(0,20);
		}
		return result;
	}
	
	private static void handleSpecialOperation(Label lb1, Label lb2, String operation) {
		if(temporaryResult.matches("\\d*[\\.][0]")) temporaryResult = temporaryResult.substring(0,temporaryResult.length()-2);
		if(!specialOperationInProgress) {
			lb2.setText(lb2.getText() + String.format(" %s(%s)",operation,lb1.getText()));
			InputHandler.isOverridible = true;
			specialOperationInProgress = true;
		} else lb2.setText(handleMultipleSpecialOperations(lb2,operation));
		lb1.setText((temporaryResult.length() > 20) ? temporaryResult.substring(0,20) : temporaryResult);
	}
	
	private static String handleMultipleSpecialOperations(Label label, String s) {
		String txt = label.getText().split(" ")[label.getText().split(" ").length - 1];
		StringBuilder sb = new StringBuilder(label.getText());
		int index = sb.lastIndexOf(" ");
		sb.delete(index,sb.length());
		sb.append(String.format(" %s(%s)",s,txt));
		return sb.toString();
	}
}
