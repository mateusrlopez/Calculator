package model.utils;

import java.awt.Toolkit;

import javafx.scene.control.Label;

public class InputHandler implements Constants {
	private static Label lb1;
	private static Label lb2;
	
	public static boolean isOverridible;
	
	public static void handleBackspace() {
		if(lb1.getText().length() > 0 && !isOverridible) {
			String newTxt = Backsp.apply(lb1.getText());
			if(newTxt.matches("([\\-])?")) {
				lb1.setText("0");
				isOverridible = true;
			} else lb1.setText(newTxt);
		} else Toolkit.getDefaultToolkit().beep();
	}
	
	public static void handleNumberAndPoints(String txt) {
		if(txt.matches("\\d*")) {
			if(isOverridible) {
				lb1.setText(txt);
				if(!txt.equals("0")) isOverridible = false;
				if(OperationHandler.specialOperationInProgress) {
					clearSpecialOperation();
					OperationHandler.specialOperationInProgress = false;
				}
			} else lb1.setText(lb1.getText() + txt);
		} else {
			if(isOverridible) {
				lb1.setText("0.");
				isOverridible = false;
				if(OperationHandler.specialOperationInProgress) {
					clearSpecialOperation();
					OperationHandler.specialOperationInProgress = false;
				}
			} else lb1.setText(lb1.getText() + txt);
		}
	}
	
	public static void handleNegate() {
		if(!lb1.getText().equals("0")) {
			lb1.setText((lb1.getText().matches("[\\-]\\d*([\\.]\\d*)?"))? new StringBuilder(lb1.getText()).deleteCharAt(0).toString() :
					"-"+lb1.getText());	
			isOverridible = false;
		}
	}
	
	public static void clearSpecialOperation() {
		StringBuilder sb = new StringBuilder(lb2.getText());
		int index = sb.lastIndexOf(" ");
		sb.delete(index, sb.length());
		lb2.setText(sb.toString());
	}
	
	public static void setLabels(Label lb1, Label lb2) {
		InputHandler.lb1 = lb1;
		InputHandler.lb2 = lb2;
	}
}
