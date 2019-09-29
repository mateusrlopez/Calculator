package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.gui.MainViewController;

public class Main extends Application {	
	@Override public void start (Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/gui/MainCalculator.fxml"));
			Parent parent = (Parent)loader.load();
			Scene scene = new Scene(parent);
			MainViewController controller = (MainViewController) loader.getController();
			stage.setScene(scene);
			controller.stageSetters(stage,scene);			
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Calculadora");
			stage.getIcons().add(new Image("/view/images/Calculadora.png"));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
