package ch.pg.sinkships.controller;

import ch.pg.sinkships.model.Game;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controllerplay {

	static int X, Y;

	@FXML
	protected GridPane griden, gridyou;

	Game Game = new Game();
	
	private Image picture;

	/**
	 * get klicked Item
	 * 
	 * @param e
	 */
	@SuppressWarnings("static-access")
	@FXML
	protected void Hit(MouseEvent e) {
		ImageView hit = (ImageView) e.getSource();
		if (hit.isDisable() == false) {
			hit.setOpacity(0.90);
			hit.setDisable(true);
			X = griden.getRowIndex(hit);
			Y = griden.getColumnIndex(hit);
			if (Game.getActualTable() == "table1") {
				Game.table2.checkforhit(X, Y);
			} else if (Game.getActualTable() == "table2") {
				Game.table1.checkforhit(X, Y);
			}
			if(Game.table1.isCheckforhit() == true || Game.table2.isCheckforhit() == true) {
				picture = new Image("/ch/pg/sinkships/sources/x.jpg");
			}else {
				picture = new Image("/ch/pg/sinkships/sources/dot.jpg");
			}
			hit.setImage(picture);
			Game.table1.setCheckforhit(false);
			Game.table2.setCheckforhit(false);
		}
	}
}
