package model.utils;

import java.awt.Toolkit;

import javafx.scene.control.Label;

public class InputHandler {
	public static boolean isOverridible;
	
	public static void handleBackspace(Label lb1) {
		if(lb1.getText().length() > 0 && !isOverridible) {
			String newTxt = Constants.BCKSP_FUNC.apply(lb1.getText());
			if(newTxt.matches("([\\-])?")) {
				lb1.setText("0");
				isOverridible = true;
			} else lb1.setText(newTxt);
		} else Toolkit.getDefaultToolkit().beep();
	}
	
	public static void handleNumberAndPoints(String txt, Label lb1, Label lb2) {
		if(txt.matches("\\d*")) {
			if(isOverridible) {
				lb1.setText(txt);
				if(!txt.equals("0")) isOverridible = false;
				if(OperationHandler.specialOperationInProgress) {
					clearSpecialOperation(lb2);
					OperationHandler.specialOperationInProgress = false;
				}
			}
			else lb1.setText(lb1.getText() + txt);
		} else {
			if(isOverridible) {
				lb1.setText("0.");
				isOverridible = false;
				if(OperationHandler.specialOperationInProgress) {
					clearSpecialOperation(lb2);
					OperationHandler.specialOperationInProgress = false;
				}
			}
			else lb1.setText(lb1.getText() + txt);
		}
	}
	
	public static void handleNegate(Label lb1) {
		if(!lb1.getText().equals("0")) {
			lb1.setText((lb1.getText().matches("[\\-]\\d*([\\.]\\d*)?"))? new StringBuilder(lb1.getText()).deleteCharAt(0).toString() :
					"-"+lb1.getText());	
			isOverridible = false;
		}
	}
	
	public static void clearSpecialOperation(Label label) {
		StringBuilder sb = new StringBuilder(label.getText());
		int index = sb.lastIndexOf(" ");
		sb.delete(index, sb.length());
		label.setText(sb.toString());
	}
}
