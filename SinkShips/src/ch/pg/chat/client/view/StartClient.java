package ch.pg.chat.client.view;

import java.io.IOException;
import java.net.URL;

import ch.pg.chat.client.view.ClientControll;
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
public class StartClient extends Application {

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

		primaryStage.setTitle("Chat: Client");
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
			URL resourcePath = ClientControll.class.getResource(name + ".fxml");
			parentToLoad = FXMLLoader.load(resourcePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene sceneToLoad = new Scene(parentToLoad);
		mainStage.setScene(sceneToLoad);
	}
}
