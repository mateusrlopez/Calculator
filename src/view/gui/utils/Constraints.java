package view.gui.utils;

import java.awt.Toolkit;

import javafx.scene.control.Label;
import model.utils.Constants;

public class Constraints {
	public static void setLabelMaxLength(Label label, int max) {
		label.textProperty().addListener((obj,oldValue,newValue) -> {
			if(newValue != null && newValue.length() > max) {
				Toolkit.getDefaultToolkit().beep();
				label.setText(oldValue);
			}
		});
	}

	public static void setLabelDouble(Label label) {
		label.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches(Constants.Regex1)) 
				label.setText(oldValue);
		});
	}	
	
	public static void setLabelNonNull(Label label) {
		label.textProperty().addListener((obs, oldValue, newValue) -> {
			if(newValue != null && newValue.equals("00") && oldValue.equals("0"))
				label.setText(oldValue);
		});
	}
	
	public static void setLabelResizable(Label label) {
		label.textProperty().addListener((obs, oldValue, newValue) -> {
			if(newValue != null && newValue.length() < 14) 
				label.setStyle("-fx-font-size: 20pt");
			else
				label.setStyle("-fx-font-size: 17pt");
		});
	}
}
