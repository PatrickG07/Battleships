package ch.pg.sinkships.controller;

import java.awt.MouseInfo;
import java.awt.Point;

import ch.pg.sinkships.model.Game;
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
		if (Game.table1.Ship1.getPos1X() != 0 && Game.table1.Ship2.getPos1X() != 0 && Game.table1.Ship3.getPos1X() != 0
				&& Game.table1.Ship4.getPos1X() != 0 && Game.table1.Ship5.getPos1X() != 0) {
			if (Game.table1.Ship1.getPos2X() != 0 && Game.table1.Ship2.getPos3X() != 0
					&& Game.table1.Ship3.getPos3X() != 0 && Game.table1.Ship4.getPos4X() != 0
					&& Game.table1.Ship5.getPos5X() != 0) {
				end = true;
				Game.table1.ip = TFip.getText();

				StartSinkShips.loadScene("/ch/pg/sinkships/view/Playground");
			}
		} else {
			String ship1 = "1: not placed ", ship2 = "2: not placed ", ship3 = "3: not placed ",
					ship4 = "4: not placed ", ship5 = "5: not placed ";
			if (Game.table1.Ship1.getPos1X() != 0 || Game.table1.Ship1.getPos2X() != 0) {
				ship1 = "";
			}
			if (Game.table1.Ship2.getPos1X() != 0 || Game.table1.Ship2.getPos3X() != 0) {
				ship2 = "";
			}
			if (Game.table1.Ship3.getPos1X() != 0 || Game.table1.Ship3.getPos3X() != 0) {
				ship3 = "";
			}
			if (Game.table1.Ship4.getPos1X() != 0 || Game.table1.Ship4.getPos4X() != 0) {
				ship4 = "";
			}
			if (Game.table1.Ship5.getPos1X() != 0 || Game.table1.Ship5.getPos5X() != 0) {
				ship5 = "";
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
	 * if The Rectangle was Licked it will Rotate for 90° or in this Case it changes
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
			if (Game.table1.Ship1.getHorizontal()) {
				rec1.setHeight(60);
				rec1.setWidth(30);
				Game.table1.Ship1.setHorizontal(false);
			} else {
				rec1.setHeight(30);
				rec1.setWidth(60);
				Game.table1.Ship1.setHorizontal(true);
			}
			break;
		case "rec2":
			if (Game.table1.Ship2.getHorizontal()) {
				rec2.setHeight(90);
				rec2.setWidth(30);
				Game.table1.Ship2.setHorizontal(false);
			} else {
				rec2.setHeight(30);
				rec2.setWidth(90);
				Game.table1.Ship2.setHorizontal(true);
			}
			break;
		case "rec3":
			if (Game.table1.Ship3.getHorizontal()) {
				rec3.setHeight(90);
				rec3.setWidth(30);
				Game.table1.Ship3.setHorizontal(false);
			} else {
				rec3.setHeight(30);
				rec3.setWidth(90);
				Game.table1.Ship3.setHorizontal(true);
			}
			break;
		case "rec4":
			if (Game.table1.Ship4.getHorizontal()) {
				rec4.setHeight(120);
				rec4.setWidth(30);
				Game.table1.Ship4.setHorizontal(false);
			} else {
				rec4.setHeight(30);
				rec4.setWidth(120);
				Game.table1.Ship4.setHorizontal(true);
			}
			break;
		case "rec5":
			if (Game.table1.Ship5.getHorizontal()) {
				rec5.setHeight(150);
				rec5.setWidth(30);
				Game.table1.Ship5.setHorizontal(false);
			} else {
				rec5.setHeight(30);
				rec5.setWidth(150);
				Game.table1.Ship5.setHorizontal(true);
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
			rec1.setX(p.x - posX - 10);
			rec1.setY(p.y - posY - 35);
		} else if (drag2 == true) {
			rec2.setX(p.x - posX - 10);
			rec2.setY(p.y - posY - 35);
		} else if (drag3 == true) {
			rec3.setX(p.x - posX - 10);
			rec3.setY(p.y - posY - 35);
		} else if (drag4 == true) {
			rec4.setX(p.x - posX - 10);
			rec4.setY(p.y - posY - 35);
		} else if (drag5 == true) {
			rec5.setX(p.x - posX - 10);
			rec5.setY(p.y - posY - 35);
		}
	}

	/**
	 * creates the Ships and checks if the Ships has the correct Distance to each
	 * other
	 */
	private void createBoats() {
		X = gridyou.getLayoutX() + 15;

		int sip1 = 2, sip2 = 3, sip3 = 3, sip4 = 4, sip5 = 5;
		System.out.println("top left " + X + " " + Y);
		/**
		 * Placing the ships
		 */
		for (int y = 1; y <= 10; y++) {
			Y = gridyou.getLayoutY() + 15;
			X = X + 30;
			for (int x = 1; x <= 10; x++) {
				Y = Y + 30;
				if (X > rec1.getX() && X < rec1.getX() + rec1.getWidth() && Y > rec1.getY()
						&& Y < rec1.getY() + rec1.getHeight()) {
					if (sip1 == 2) {
						Game.table1.Ship1.setPos1X(x);
						Game.table1.Ship1.setPos1Y(y);
						System.out.println("true1");
					} else if (sip1 == 1) {
						Game.table1.Ship1.setPos2X(x);
						Game.table1.Ship1.setPos2Y(y);
						System.out.println("true2");
					}
					sip1--;
				}
				if (X > rec2.getX() && X < rec2.getX() + rec2.getWidth() && Y > rec2.getY()
						&& Y < rec2.getY() + rec2.getHeight()) {
					if (sip2 == 3) {
						Game.table1.Ship2.setPos1X(x);
						Game.table1.Ship2.setPos1Y(y);
					} else if (sip2 == 2) {
						Game.table1.Ship2.setPos2X(x);
						Game.table1.Ship2.setPos2Y(y);
					} else if (sip2 == 1) {
						Game.table1.Ship2.setPos3X(x);
						Game.table1.Ship2.setPos3Y(y);
					}
					sip2--;
				}
				if (X > rec3.getX() && X < rec3.getX() + rec3.getWidth() && Y > rec3.getY()
						&& Y < rec3.getY() + rec3.getHeight()) {
					if (sip3 == 3) {
						Game.table1.Ship3.setPos1X(x);
						Game.table1.Ship3.setPos1Y(y);
					} else if (sip3 == 2) {
						Game.table1.Ship3.setPos2X(x);
						Game.table1.Ship3.setPos2Y(y);
					} else if (sip3 == 1) {
						Game.table1.Ship3.setPos3X(x);
						Game.table1.Ship3.setPos3Y(y);
					}
					sip3--;
				}
				if (X > rec4.getX() && X < rec4.getX() + rec4.getWidth() && Y > rec4.getY()
						&& Y < rec4.getY() + rec4.getHeight()) {
					if (sip4 == 4) {
						Game.table1.Ship4.setPos1X(x);
						Game.table1.Ship4.setPos1Y(y);
					} else if (sip4 == 3) {
						Game.table1.Ship4.setPos2X(x);
						Game.table1.Ship4.setPos2Y(y);
					} else if (sip4 == 2) {
						Game.table1.Ship4.setPos3X(x);
						Game.table1.Ship4.setPos3Y(y);
					} else if (sip4 == 1) {
						Game.table1.Ship4.setPos4X(x);
						Game.table1.Ship4.setPos4Y(y);
					}
					sip4--;
				}
				if (X > rec5.getX() && X < rec5.getX() + rec5.getWidth() && Y > rec5.getY()
						&& Y < rec5.getY() + rec5.getHeight()) {
					if (sip5 == 5) {
						Game.table1.Ship5.setPos1X(x);
						Game.table1.Ship5.setPos1Y(y);
					} else if (sip5 == 4) {
						Game.table1.Ship5.setPos2X(x);
						Game.table1.Ship5.setPos2Y(y);
					} else if (sip5 == 3) {
						Game.table1.Ship5.setPos3X(x);
						Game.table1.Ship5.setPos3Y(y);
					} else if (sip5 == 2) {
						Game.table1.Ship5.setPos4X(x);
						Game.table1.Ship5.setPos4Y(y);
					} else if (sip5 == 1) {
						Game.table1.Ship5.setPos5X(x);
						Game.table1.Ship5.setPos5Y(y);
					}
					sip5--;
				}
			}
		}

		// -11 -10 -9
		// -1 0 +1
		// +9 +10 +11

		for (int o = 1; o <= 5; o++) {
			for (int p = 1; p <= 5; p++) {
				for (int i = 1; i <= 9; i++) {
					int ran = 0;
					switch (i) {
					case 1:
						ran = -11;
						break;
					case 2:
						ran = -10;
						break;
					case 3:
						ran = -9;
						break;
					case 4:
						ran = -1;
						break;
					case 6:
						ran = 1;
						break;
					case 7:
						ran = 9;
						break;
					case 8:
						ran = 10;
						break;
					case 9:
						ran = 11;
						break;
					}

					// Ship 1
					System.out.println("o / p " + o + " " + p);
					if (p < 3) {
						if (Game.table1.Ship1.getPos(p) < 2000) {
							if (Game.table1.Ship1.getPos(p) + ran == Game.table1.Ship2.getPos(o)) {
								System.out.println("true11*******************************************************");
								System.out.println(Game.table1.Ship1.getPos(p) + " +  " + ran + "  "
										+ Game.table1.Ship2.getPos(o));

								Game.table1.Ship1.setPos1X(0);
							}
							if (Game.table1.Ship1.getPos(p) + ran == Game.table1.Ship3.getPos(o)) {
								System.out.println("true12*******************************************************");
								System.out.println(Game.table1.Ship1.getPos(p) + " +  " + ran + "  "
										+ Game.table1.Ship3.getPos(o));

								Game.table1.Ship1.setPos1X(0);
							}
							if (Game.table1.Ship1.getPos(p) + ran == Game.table1.Ship4.getPos(o)) {
								System.out.println("true13******************************************************");
								System.out.println(Game.table1.Ship1.getPos(p) + " +  " + ran + "   "
										+ Game.table1.Ship4.getPos(o));

								Game.table1.Ship1.setPos1X(0);
							}
							if (Game.table1.Ship1.getPos(p) + ran == Game.table1.Ship5.getPos(o)) {
								System.out.println("true14*******************************************************");
								System.out.println(Game.table1.Ship1.getPos(p) + " +  " + ran + "  "
										+ Game.table1.Ship5.getPos(o));

								Game.table1.Ship1.setPos1X(0);
								Game.table1.Ship5.setPos1X(0);
							}
						}
					}
					// ship 2
					if (p < 4) {
						if (Game.table1.Ship2.getPos(p) < 2000) {
							if (Game.table1.Ship2.getPos(p) + ran == Game.table1.Ship3.getPos(o)) {
								System.out.println("true21*******************************************************");
								Game.table1.Ship2.setPos1X(0);
							}
							if (Game.table1.Ship2.getPos(p) + ran == Game.table1.Ship4.getPos(o)) {
								System.out.println("true22*******************************************************");
								Game.table1.Ship2.setPos1X(0);
							}
							if (Game.table1.Ship2.getPos(p) + ran == Game.table1.Ship5.getPos(o)) {
								System.out.println("true23*******************************************************");
								Game.table1.Ship2.setPos1X(0);
								Game.table1.Ship5.setPos1X(0);
							}
						}
					}
					// ship 3
					if (p < 4) {
						if (Game.table1.Ship3.getPos(p) < 2000) {
							if (Game.table1.Ship3.getPos(p) + ran == Game.table1.Ship4.getPos(o)) {
								System.out.println("true31*******************************************************");
								Game.table1.Ship3.setPos1X(0);
							}
							if (Game.table1.Ship3.getPos(p) + ran == Game.table1.Ship5.getPos(o)) {
								System.out.println("true32*******************************************************");
								Game.table1.Ship3.setPos1X(0);
								Game.table1.Ship5.setPos1X(0);
							}
						}
					}
					// ship 4
					if (p < 5) {
						if (Game.table1.Ship4.getPos(p) < 2000) {
							if (Game.table1.Ship4.getPos(p) + ran == Game.table1.Ship5.getPos(o)) {
								System.out.println("true41*******************************************************");
								Game.table1.Ship4.setPos1X(0);
								Game.table1.Ship5.setPos1X(0);
							}
						}
					}
					// ship 5 not needed already got checked
				}
			}
		}
	}
}
