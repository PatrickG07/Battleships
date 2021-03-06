package ch.pg.sinkships.controller;

import java.awt.MouseInfo;
import java.awt.Point;

import ch.pg.sinkships.model.Game;
import ch.pg.sinkships.model.Ship;
import ch.pg.sinkships.view.StartSinkShips;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Controller class for set the Sips at the Locations
 * 
 * @author PatrickG07
 */
public class Controllerplayer {

	private static final Duration TRANSLATE_DURATION = Duration.millis(1);

	boolean again = true, end = false, drag1 = false, drag2 = false, drag3 = false, drag4 = false, drag5 = false;

	boolean server = false;

	double X, Y, L = 0;

	int x, y;

	MouseEvent event;

	@FXML
	protected AnchorPane AnchorPane;

	@FXML
	protected Button btserver, btclient;

	@FXML
	TextField TFip;

	@FXML
	protected Rectangle rec1, rec2, rec3, rec4, rec5;

	Rectangle[] recArr = new Rectangle[] { rec1, rec2, rec3, rec4, rec5 };

	@FXML
	protected GridPane griden, gridyou;

	@FXML
	protected Text errortext;

	@FXML
	protected ImageView box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11, box12, box13, box14, box15,
			box16, box17, box18, box19, box20, box21, box22, box23, box24, box25, box26, box27, box28, box29, box30,
			box31, box32, box33, box34, box35, box36, box37, box38, box39, box40, box41, box42, box43, box44, box45,
			box46, box47, box48, box49, box50, box51, box52, box53, box54, box55, box56, box57, box58, box59, box60,
			box61, box62, box63, box64, box65, box66, box67, box68, box69, box70, box71, box72, box73, box74, box75,
			box76, box77, box78, box79, box80, box81, box82, box83, box84, box85, box86, box87, box88, box89, box90,
			box91, box92, box93, box94, box95, box96, box97, box98, box99, box100;

	/**
	 * Translate for dragging the Rectangle with the mouse at the same time.
	 * 
	 * @return
	 */
	private TranslateTransition createTranslateTransition() {
		recArr[0] = rec1;
		recArr[1] = rec2;
		recArr[2] = rec3;
		recArr[3] = rec4;
		recArr[4] = rec5;
		final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, rec1);
		transition.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Dragging();
				moveRectangle(transition);
			}
		});
		return transition;
	}

	/**
	 * Repeat the Transition. for dragging the Rectangles.
	 * 
	 * @param transition
	 */
	private void moveRectangle(final TranslateTransition transition) {
		if (end == false) {
			transition.playFromStart();
		} else {
			transition.stop();
		}
	}

	/**
	 * If the ships are correctly placed it goes to the next scene
	 * 
	 * @param e
	 */
	@FXML
	protected void Play(ActionEvent e) {
		createBoats();
		if (Game.table1.ship[0].getPos(0, 0) != 0 && Game.table1.ship[1].getPos(0, 0) != 0
				&& Game.table1.ship[2].getPos(0, 0) != 0 && Game.table1.ship[3].getPos(0, 0) != 0
				&& Game.table1.ship[4].getPos(0, 0) != 0 && Game.table1.ship[0].getPos(0, 1) != 0
				&& Game.table1.ship[1].getPos(0, 1) != 0 && Game.table1.ship[2].getPos(0, 1) != 0
				&& Game.table1.ship[3].getPos(0, 1) != 0 && Game.table1.ship[4].getPos(0, 1) != 0) {
			end = true;
			Game.table1.ip = TFip.getText();

			StartSinkShips.loadScene("/ch/pg/sinkships/view/Playground");
		} else {
			String ship1 = "1: not placed ", ship2 = "2: not placed ", ship3 = "3: not placed ",
					ship4 = "4: not placed ", ship5 = "5: not placed ";
			int num = 1;
			for (Ship s : Game.table1.ship) {
				if (s.getPos(0, 0) != 0 || s.getPos(0, 1) != 0) {
					System.out.println(java.util.Arrays.asList(Game.table1.ship).indexOf(s) + " " + s.getPos(0, 0) + " : " + s.getPos(0, 1));
					ship1 = "";
					switch (num) {
					case 1:
						ship1 = "";
						break;
					case 2:
						ship2 = "";
						break;
					case 3:
						ship3 = "";
						break;
					case 4:
						ship4 = "";
						break;
					case 5:
						ship5 = "";
						break;
					}
				}
				num++;
			}
			errortext.setText(ship1 + ship2 + ship3 + ship4 + ship5);
		}
	}

	/**
	 * Returns to the main Controller (Back to the Start Scene).
	 * 
	 * @param e
	 */
	@FXML
	protected void Back(ActionEvent e) {
		end = true;
		StartSinkShips.loadScene("/ch/pg/sinkships/view/Main");
	}

	@FXML
	protected void Server(ActionEvent e) {
		btserver.setStyle("-fx-background-color: #b4ccdb");
		btclient.setStyle("-fx-background-color: #ffffff");
		server = true;
		Game.actualTable = "table1";
	}

	@FXML
	protected void Client(ActionEvent e) {
		btserver.setStyle("-fx-background-color: #ffffff");
		btclient.setStyle("-fx-background-color: #b4ccdb");
		server = false;
		Game.actualTable = "table2";
	}

	/**
	 * if The Rectangle was Licked it will Rotate for 90� or in this Case it changes
	 * the Height and With of the Rectangle
	 * 
	 * @param e
	 */
	@FXML
	protected void onRotate(MouseEvent e) {
		Rectangle rec = (Rectangle) e.getSource();
		String Rac = rec.getId();
		switch (Rac) {
		case "rec1":
			if (Game.table1.ship[0].getHorizontal()) {
				recArr[0].setHeight(60);
				recArr[0].setWidth(30);
				Game.table1.ship[0].setHorizontal(false);
			} else {
				recArr[0].setHeight(30);
				recArr[0].setWidth(60);
				Game.table1.ship[0].setHorizontal(true);
			}
			break;
		case "rec2":
			if (Game.table1.ship[1].getHorizontal()) {
				recArr[1].setHeight(90);
				recArr[1].setWidth(30);
				Game.table1.ship[1].setHorizontal(false);
			} else {
				recArr[1].setHeight(30);
				recArr[1].setWidth(90);
				Game.table1.ship[1].setHorizontal(true);
			}
			break;
		case "rec3":
			if (Game.table1.ship[2].getHorizontal()) {
				recArr[2].setHeight(90);
				recArr[2].setWidth(30);
				Game.table1.ship[2].setHorizontal(false);
			} else {
				recArr[2].setHeight(30);
				recArr[2].setWidth(90);
				Game.table1.ship[2].setHorizontal(true);
			}
			break;
		case "rec4":
			if (Game.table1.ship[3].getHorizontal()) {
				recArr[3].setHeight(120);
				recArr[3].setWidth(30);
				Game.table1.ship[3].setHorizontal(false);
			} else {
				recArr[3].setHeight(30);
				recArr[3].setWidth(120);
				Game.table1.ship[3].setHorizontal(true);
			}
			break;
		case "rec5":
			if (Game.table1.ship[4].getHorizontal()) {
				recArr[4].setHeight(150);
				recArr[4].setWidth(30);
				Game.table1.ship[4].setHorizontal(false);
			} else {
				recArr[4].setHeight(30);
				recArr[4].setWidth(150);
				Game.table1.ship[4].setHorizontal(true);
			}
			break;
		}
	}

	/**
	 * Gets the id of the Rectangle and set the drag on true
	 * 
	 * @param e
	 */
	@FXML
	protected void onDrag(MouseEvent e) {
		event = e;
		Rectangle rec = (Rectangle) e.getSource();
		switch (rec.getId()) {
		case "rec1":
			drag1 = true;
			break;
		case "rec2":
			drag2 = true;
			break;
		case "rec3":
			drag3 = true;
			break;
		case "rec4":
			drag4 = true;
			break;
		case "rec5":
			drag5 = true;
			break;
		}
		if (again = true) {
			final TranslateTransition transition = createTranslateTransition();
			moveRectangle(transition);
			again = false;
		}
	}

	/**
	 * if the Rectangle is dropped (mouse released) then set the Rectangle to the
	 * position of the Mouse
	 * 
	 * @param e
	 */
	@FXML
	protected void onDrop(MouseEvent e) {
		event = e;
		Dragging();
		drag1 = false;
		drag2 = false;
		drag3 = false;
		drag4 = false;
		drag5 = false;
	}

	/**
	 * Dragging the Rectangle with the Mouse.
	 */
	private void Dragging() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		// p is at left top position (x = 0 y = 0 on Screen means x = 0 y = 0 in the
		// window)

		double posX = 0;
		double posY = 0;
		posX = StartSinkShips.getPosX();
		posY = StartSinkShips.getPosY();

		if (drag1 == true) {
			recArr[0].setX(p.x - posX - 10);
			recArr[0].setY(p.y - posY - 35);
		} else if (drag2 == true) {
			recArr[1].setX(p.x - posX - 10);
			recArr[1].setY(p.y - posY - 35);
		} else if (drag3 == true) {
			recArr[2].setX(p.x - posX - 10);
			recArr[2].setY(p.y - posY - 35);
		} else if (drag4 == true) {
			recArr[3].setX(p.x - posX - 10);
			recArr[3].setY(p.y - posY - 35);
		} else if (drag5 == true) {
			recArr[4].setX(p.x - posX - 10);
			recArr[4].setY(p.y - posY - 35);
		}
	}

	/**
	 * creates the Ships and checks if the Ships has the correct Distance to each
	 * other
	 */
	private void createBoats() {
		X = gridyou.getLayoutX() + 15;

		// sip1 = 2, sip2 = 3, sip3 = 3, sip4 = 4, sip5 = 5

		int[] sip = new int[] { 2, 3, 3, 4, 5 };

		/**
		 * Placing the ships
		 */
		for (int y = 1; y <= 10; y++) {
			Y = gridyou.getLayoutY() + 15;
			X = X + 30;
			for (int x = 1; x <= 10; x++) {
				Y = Y + 30;
				for (int r = 0; r <= 4; r++) {
					if (X > recArr[r].getX() && X < recArr[r].getX() + recArr[r].getWidth() && Y > recArr[r].getY()
							&& Y < recArr[r].getY() + recArr[r].getHeight()) {
						if (sip[r] > 0) {
							Game.table1.ship[r].setPos(sip[r] - 1, 0, x);
							Game.table1.ship[r].setPos(sip[r] - 1, 1, y);
							sip[r]--;
						}
					}
				}
			}
		}

		// -11 -10 -9
		// -1 0 +1
		// +9 +10 +11

		for (int o = 1; o <= 5; o++) {
			for (int p = 1; p <= 5; p++) {
				for (int i = 1; i <= 9; i++) {
					int ArrRan[] = new int[] {-11, -10, -9, -1, 1, 9, 10, 11};
					int ran = ArrRan[i];
					
					for (int t = 0; t <= 4; t++) {
						if (Game.table1.ship[t].getPos(p) < 2000 || Game.table1.ship[t].getPos(p) != 1000000) {
							for (int s = t + 1; s <= 4; s++) {
								if (Game.table1.ship[t].getPos(p) + ran == Game.table1.ship[s].getPos(o)) {
									Game.table1.ship[t].setPos(0, 0, 0);
									Game.table1.ship[s].setPos(0, 0, 0);
								}
							}
						}
					}
				}
			}
		}
	}
}