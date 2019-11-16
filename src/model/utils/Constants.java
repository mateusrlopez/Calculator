package model.utils;

import java.util.Map;
import java.util.function.Function;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public interface Constants {
	KeyCombination PlusComb = new KeyCodeCombination(KeyCode.EQUALS,KeyCodeCombination.SHIFT_DOWN);
	KeyCombination MultComb = new KeyCodeCombination(KeyCode.DIGIT8,KeyCodeCombination.SHIFT_DOWN);
	KeyCombination ModComb = new KeyCodeCombination(KeyCode.DIGIT5,KeyCodeCombination.SHIFT_DOWN);
	KeyCombination SqrtComb = new KeyCodeCombination(KeyCode.DIGIT2,KeyCodeCombination.SHIFT_DOWN);
	String Regex1 = "(\\-)?\\d*([\\.]\\d*)?([E](\\-)?\\d*)?";
	Function<String,String> Backsp = p -> new StringBuilder(p).deleteCharAt(p.length()-1).toString();
	Map<String,String> SpecialOP = Map.of("Reciproc", "reciproc","√","sqrt","x²", "square","x³","cube");
}
