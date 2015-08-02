package pl.edu.agh.kis.interlight.fx;

import java.util.Locale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainApp extends Application {

	private static final Logger logger = LogManager.getLogger(MainApp.class
			.getName());

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		Locale.setDefault(Locale.US);
		FXMLLoader loader = new FXMLLoader();
		Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(
				"/fxml/main.fxml"));

		Scene scene = new Scene(rootNode);
		scene.getStylesheets().add("/styles/style.css");

		stage.setTitle("InterLight");
		stage.setScene(scene);
		stage.show();

		logger.info("Application 'InterLight' started");

	}
}
