package ch.pg.sinkships.view;

import java.io.IOException;
import java.net.URL;

import ch.pg.sinkships.controller.Controllerstart;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The first Class.
 * 
 * @author PatrickG07
 */
public class StartSinkShips extends Application {

	public static Stage mainStage;

	public static void main(String... args) {
		launch(args);
	}

	/**
	 * Starts the program.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setTitle("Sinkships");
		primaryStage.centerOnScreen();
		primaryStage.setScene(scene);
		primaryStage.show();

		mainStage = primaryStage;
	}

	/**
	 * changes the FXML scene.
	 * 
	 * @param name
	 */
	public static void loadScene(String name) {
		Parent parentToLoad = null;

		try {
			URL resourcePath = Controllerstart.class.getResource(name + ".fxml");
			parentToLoad = FXMLLoader.load(resourcePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene sceneToLoad = new Scene(parentToLoad);
		mainStage.setScene(sceneToLoad);
	}
}
