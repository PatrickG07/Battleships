package ch.pg.sinkships.controller;

import ch.pg.sinkships.model.Game;
import ch.pg.sinkships.view.StartSinkShips;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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

	double X, Y, L = 0;

	int x, y;

	MouseEvent event;

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
	 * 
	 */
	@FXML
	public void initialize() {
		System.out.println("test0");
	}

	private TranslateTransition createTranslateTransition() {
		final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION);
		transition.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {

				L++;
				errortext.setText("" + L);
				Dragging();

				moveCircleOnSpacePress(transition);
			}
		});
		return transition;
	}

	private void moveCircleOnSpacePress(final TranslateTransition transition) {
		if (end == false) {
//			transition.playFromStart();
		} else {
			errortext.setText("testtttasdfhsdalkfhjasdlf");
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
				&& Game.table1.Ship4.getPos1X() != 0 && Game.table1.Ship5.getPos1X() != 0
				&& Game.table1.Ship1.getPos2X() != 0 && Game.table1.Ship2.getPos3X() != 0
				&& Game.table1.Ship3.getPos3X() != 0 && Game.table1.Ship4.getPos4X() != 0
				&& Game.table1.Ship5.getPos5X() != 0) {
			end = true;
			StartSinkShips.loadScene("/ch/pg/sinkships/view/Playground");
		} else {
			errortext.setText("Ships are not placed");
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
		// speed up the ball / circle after X bounces by the Rectangulars
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
		// speed up the ball / circle after X bounces by the Rectangulars
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
			// createTranslateTransition();
			moveCircleOnSpacePress(transition);
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
	 * 
	 */
	private void Dragging() {
		if (drag1 == true) {
			rec1.setX(event.getSceneX());
			rec1.setY(event.getSceneY());
		} else if (drag2 == true) {
			rec2.setX(event.getSceneX());
			rec2.setY(event.getSceneY());
		} else if (drag3 == true) {
			rec3.setX(event.getSceneX());
			rec3.setY(event.getSceneY());
		} else if (drag4 == true) {
			rec4.setX(event.getSceneX());
			rec4.setY(event.getSceneY());
		} else if (drag5 == true) {
			rec5.setX(event.getSceneX());
			rec5.setY(event.getSceneY());
		}
	}

	/**
	 * creates the Ships and checks if the Ships has the correct Distance to each
	 * other
	 */
	private void createBoats() {
		X = gridyou.getLayoutX() + 15;
		Y = gridyou.getLayoutY() + 15;

		int sip1 = 2, sip2 = 3, sip3 = 3, sip4 = 4, sip5 = 5;

		for (int x = 1; x < 11; x++) {
			Y = Y + 30;
			X = gridyou.getLayoutX() + 15;
			for (int y = 1; y < 11; y++) {
				X = X + 30;
				if (X > rec1.getX() && X < rec1.getX() + rec1.getWidth() && Y > rec1.getY()
						&& Y < rec1.getY() + rec1.getHeight()) {
					if (sip1 == 2) {
						Game.table1.Ship1.setPos1X(x);
						Game.table1.Ship1.setPos1Y(y);
					} else if (sip1 == 1) {
						Game.table1.Ship1.setPos2X(x);
						Game.table1.Ship1.setPos2Y(y);
					}
					sip1--;
				} else if (X > rec2.getX() && X < rec2.getX() + rec2.getWidth() && Y > rec2.getY()
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
				} else if (X > rec3.getX() && X < rec3.getX() + rec3.getWidth() && Y > rec3.getY()
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
				} else if (X > rec4.getX() && X < rec4.getX() + rec4.getWidth() && Y > rec4.getY()
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
				} else if (X > rec5.getX() && X < rec5.getX() + rec5.getWidth() && Y > rec5.getY()
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

		// check if rec2, rec3, rec4, rec5 is near rec1
		if (rec1.getX() - 30 < rec2.getX() + 15
				&& rec1.getX() + rec1.getWidth() + 30 > rec2.getX() + rec2.getWidth() - 15
				&& rec1.getY() - 30 < rec2.getX() + 15
				&& rec1.getY() + rec1.getHeight() + 30 > rec2.getY() + rec2.getHeight() - 15
				|| rec1.getX() - 30 < rec3.getX() + 15
						&& rec1.getX() + rec1.getWidth() + 30 > rec3.getX() + rec3.getWidth() - 15
						&& rec1.getY() - 30 < rec3.getX() + 15
						&& rec1.getY() + rec1.getHeight() + 30 > rec3.getY() + rec3.getHeight() - 15
				|| rec1.getX() - 30 < rec4.getX() + 15
						&& rec1.getX() + rec1.getWidth() + 30 > rec4.getX() + rec4.getWidth() - 15
						&& rec1.getY() - 30 < rec4.getX() + 15
						&& rec1.getY() + rec1.getHeight() + 30 > rec4.getY() + rec4.getHeight() - 15
				|| rec1.getX() - 30 < rec5.getX() + 15
						&& rec1.getX() + rec1.getWidth() + 30 > rec5.getX() + rec5.getWidth() - 15
						&& rec1.getY() - 30 < rec5.getX() + 15
						&& rec1.getY() + rec1.getHeight() + 30 > rec5.getY() + rec5.getHeight() - 15) {
			Game.table1.Ship1.setPos1X(0);
		}
		// check if rec3, rec4, rec5 is near rec2
		if (rec2.getX() - 30 < rec3.getX() + 15
				&& rec2.getX() + rec2.getWidth() + 30 > rec3.getX() + rec3.getWidth() - 15
				&& rec2.getY() - 30 < rec3.getX() + 15
				&& rec2.getY() + rec2.getHeight() + 30 > rec3.getY() + rec3.getHeight() - 15
				|| rec2.getX() - 30 < rec4.getX() + 15
						&& rec2.getX() + rec2.getWidth() + 30 > rec4.getX() + rec4.getWidth() - 15
						&& rec2.getY() - 30 < rec4.getX() + 15
						&& rec2.getY() + rec2.getHeight() + 30 > rec4.getY() + rec4.getHeight() - 15
				|| rec2.getX() - 30 < rec5.getX() + 15
						&& rec2.getX() + rec2.getWidth() + 30 > rec5.getX() + rec5.getWidth() - 15
						&& rec2.getY() - 30 < rec5.getX() + 15
						&& rec2.getY() + rec2.getHeight() + 30 > rec5.getY() + rec5.getHeight() - 15) {
			Game.table1.Ship1.setPos1X(0);
		}
		// check if rec4, rec5 is near rec3
		if (rec3.getX() - 30 < rec4.getX() + 15
				&& rec3.getX() + rec3.getWidth() + 30 > rec4.getX() + rec4.getWidth() - 15
				&& rec3.getY() - 30 < rec4.getX() + 15
				&& rec3.getY() + rec3.getHeight() + 30 > rec4.getY() + rec4.getHeight() - 15
				|| rec3.getX() - 30 < rec5.getX() + 15
						&& rec3.getX() + rec3.getWidth() + 30 > rec5.getX() + rec5.getWidth() - 15
						&& rec3.getY() - 30 < rec5.getX() + 15
						&& rec3.getY() + rec3.getHeight() + 30 > rec5.getY() + rec5.getHeight() - 15) {
			Game.table1.Ship1.setPos1X(0);
		}
		// check if rec5 is near rec4
		if (rec4.getX() - 30 < rec5.getX() + 15
				&& rec4.getX() + rec4.getWidth() + 30 > rec5.getX() + rec5.getWidth() - 15
				&& rec4.getY() - 30 < rec5.getX() + 15
				&& rec4.getY() + rec4.getHeight() + 30 > rec5.getY() + rec2.getHeight() - 15) {
			Game.table1.Ship1.setPos1X(0);
		}
	}
}
