package ch.pg.sinkships.controller;

import ch.pg.sinkships.model.Game;
import ch.pg.sinkships.view.StartSinkShips;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller class for set player name.
 * 
 * @author Patrick
 */
public class Controllerplayer {
	
	@FXML
	protected TextField player1;
	@FXML
	protected TextField player2;

	/**
	 * set a red border around the TextFilds when nothing are tiped.
	 * 
	 * @param e
	 */
	@FXML
	protected void Play(ActionEvent e) {
		if (!player1.getText().equals("") && !player2.getText().equals("")) {
			Game.play1.setName(player1.getText());
			Game.play2.setName(player2.getText());

			StartSinkShips.loadScene("/ch/pg/sinkships/view/Playground");
		} else {
			player1.setStyle("-fx-text-box-border: red;");
			player2.setStyle("-fx-text-box-border: red;");
		}
	}

	/**
	 * Returns to the main Controller.
	 * 
	 * @param e
	 */
	@FXML
	protected void Back(ActionEvent e) {
		StartSinkShips.loadScene("/ch/pg/sinkships/view/Main");
	}

}
