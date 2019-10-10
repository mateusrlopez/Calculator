package model.utils;

import java.util.function.Function;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public interface Constants {
	KeyCombination PLUS_COMB = new KeyCodeCombination(KeyCode.EQUALS,KeyCodeCombination.SHIFT_DOWN);
	KeyCombination MULT_COMB = new KeyCodeCombination(KeyCode.DIGIT8,KeyCodeCombination.SHIFT_DOWN);
	KeyCombination MOD_COMB = new KeyCodeCombination(KeyCode.DIGIT5,KeyCodeCombination.SHIFT_DOWN);
	KeyCombination SQRT_COMB = new KeyCodeCombination(KeyCode.DIGIT2,KeyCodeCombination.SHIFT_DOWN);
	Function<String,String> BCKSP_FUNC = p -> new StringBuilder(p).deleteCharAt(p.length()-1).toString();
	String REGEX1 = "(\\-)?\\d*([\\.]\\d*)?([E](\\-)?\\d*)?";
}
