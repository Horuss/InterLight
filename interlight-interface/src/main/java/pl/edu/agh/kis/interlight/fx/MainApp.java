package pl.edu.agh.kis.interlight.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(
				"/fxml/main.fxml"));

		Scene scene = new Scene(rootNode);
		scene.getStylesheets().add("/styles/style.css");

		stage.setTitle("InterLight");
		stage.setScene(scene);
		stage.show();
	}
}
