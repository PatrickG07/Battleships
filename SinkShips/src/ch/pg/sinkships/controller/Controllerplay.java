package ch.pg.sinkships.controller;

import java.util.concurrent.TimeUnit;

import ch.pg.sinkships.controller.client.Client;
import ch.pg.sinkships.controller.server.Server;
import ch.pg.sinkships.model.Game;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * The Class for The hole Game
 * 
 * @author PatrickG07
 */
public class Controllerplay {

	public static int X, Y;

	private static boolean server;

	Node nodes;

	private static Image picture;

	@FXML
	protected GridPane griden, gridyou;

	@FXML
	protected ImageView box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11, box12, box13, box14, box15,
			box16, box17, box18, box19, box20, box21, box22, box23, box24, box25, box26, box27, box28, box29, box30,
			box31, box32, box33, box34, box35, box36, box37, box38, box39, box40, box41, box42, box43, box44, box45,
			box46, box47, box48, box49, box50, box51, box52, box53, box54, box55, box56, box57, box58, box59, box60,
			box61, box62, box63, box64, box65, box66, box67, box68, box69, box70, box71, box72, box73, box74, box75,
			box76, box77, box78, box79, box80, box81, box82, box83, box84, box85, box86, box87, box88, box89, box90,
			box91, box92, box93, box94, box95, box96, box97, box98, box99, box100;

	@FXML
	protected Text Ttext;

	/**
	 * 1. Starting Server or Connecting to a Server
	 * 2. show where your ships are
	 * 3. Reseting the destroyed ships
	 * 4. Reseting the health of the ships
	 */
	@FXML
	public void initialize() {

		if (Game.actualTable == "table1") {
			StartServer();
			griden.setDisable(true);
			server = true;
		} else if (Game.actualTable == "table2") {
			Connect();
			griden.setDisable(true);
			server = false;
		}

		for (int x = 1; x < 11; x++) {
			for (int y = 1; y < 11; y++) {
				X = x;
				Y = y;
				getNodeFromGridPaneYou();
				Game.table1.checkforhit(x, y);
				if (Game.table1.isCheckforhit() == true) {
					picture = new Image("/ch/pg/sinkships/sources/ship.jpg");
					ImageView you = (ImageView) nodes;
					you.setImage(picture);
					Game.table1.setCheckforhit(false);
				}
			}
		}

		Game.table1.Ship1.setDestroyd(false);
		Game.table1.Ship2.setDestroyd(false);
		Game.table1.Ship3.setDestroyd(false);
		Game.table1.Ship4.setDestroyd(false);
		Game.table1.Ship5.setDestroyd(false);

		Game.table1.Ship1.setHealth(2);
		Game.table1.Ship2.setHealth(3);
		Game.table1.Ship3.setHealth(3);
		Game.table1.Ship4.setHealth(4);
		Game.table1.Ship5.setHealth(5);
	}

	/**
	 * get clicked Item id 
	 * sends the id as "X-Y" to your opponent
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

			griden.setDisable(true);

			sendMessage(Integer.toString(X) + "-" + Integer.toString(Y));
		}
	}

	/**
	 * for getting the Node(source for ID) of the ImageView at the location (Row and
	 * Column) of your side (left)
	 */
	private void getNodeFromGridPaneYou() {
		for (Node node : gridyou.getChildren()) {
			if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
				if (GridPane.getColumnIndex(node) == Y && GridPane.getRowIndex(node) == X) {
					nodes = node;
				}
			}
		}
	}

	/**
	 * for getting the Node(source for ID) of the ImageView at the location (Row and
	 * Column) of your opponent side (right)
	 */
	private void getNodeFromGridPaneEnemy() {
		for (Node node : griden.getChildren()) {
			if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
				if (GridPane.getColumnIndex(node) == Y && GridPane.getRowIndex(node) == X) {
					nodes = node;
				}
			}
		}
	}

	/*
	 * if you receive something it has to be checked
	 * 1. if you hit an enemy ship
	 * 2. if you won / destroyed all enemy ships
	 * 3. at the start of the game the server gets a confirmation that the client is connected
	 * 4. if a ship is destroyed
	 * 5. getting an X and Y value to check if enemy hit
	 * 5.1. if your opponent has hit a ship
	 * 5.2. if your opponent has missed a ship
	 * 5.3. if all of your ships has Destroyed
	 */
	public void reciver(String order) {
		String trimmed = order.trim();

		if (trimmed.equals("hit") || trimmed.equals("miss")) {
			getNodeFromGridPaneEnemy();
			if (trimmed.equals("hit")) {
				picture = new Image("/ch/pg/sinkships/sources/hit.jpg");
				griden.setDisable(false);
			} else {
				picture = new Image("/ch/pg/sinkships/sources/dot.jpg");
				griden.setDisable(true);
				Ttext.setText("opponent turn");
			}

			ImageView Enemy = (ImageView) nodes;

			Enemy.setImage(picture);

		} else if (trimmed.equals("lost")) {
			End("won");
		} else if (trimmed.equals("start")) {
			griden.setDisable(false);
			Ttext.setText("your turn");
		} else if (trimmed.equals("destroyed")) {
			Ttext.setText("your turn // ship destroyed");
		} else {

			String arrStr[] = trimmed.split("-");

			X = Integer.parseInt(arrStr[0]);
			Y = Integer.parseInt(arrStr[1]);

			Game.table1.checkforhit(X, Y);

			if (Game.table1.isCheckforhit() == true) {
				picture = new Image("/ch/pg/sinkships/sources/hit.jpg");
				sendMessage("hit");
			} else {
				picture = new Image("/ch/pg/sinkships/sources/dot.jpg");
				sendMessage("miss");
				griden.setDisable(false);
				Ttext.setText("your turn");
			}
			getNodeFromGridPaneYou();
			ImageView you = (ImageView) nodes;

			Game.table1.setCheckforhit(false);

			you.setImage(picture);

			if (Game.table1.Ship1.getDestroyd() == true && Game.table1.Ship2.getDestroyd() == true
					&& Game.table1.Ship3.getDestroyd() == true && Game.table1.Ship4.getDestroyd() == true
					&& Game.table1.Ship5.getDestroyd() == true) { 

				sendMessage("lost");

				End("lost");
			}
		}
	}

	/**
	 * Sending Messages mostly Numbers (2-2), hits, misses, destroyed or lost
	 * 
	 * @param order
	 */
	public void sendMessage(String order) {
		if (server == true) {
			try {
				Server.getInstance().sendMessage(order);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		} else {
			try {
				Client.getInstance().sendMessage(order);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	/**
	 * Receiving Messages from your opponent
	 * 
	 * @param message
	 */
	public void onMessageReceived(String message) {
		reciver(message);
	}

	/**
	 * Starting Server
	 */
	public void StartServer() {
		try {
			Server.getInstance().startServer(this);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	/**
	 * Connecting as a Client to a Server
	 */
	public void Connect() {
		try {
			Client.getInstance().connectToServer(this);
			Ttext.setText("opponent turn");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	/**
	 * if one Player destroy all Ships it will display as an win for that Player
	 */
	private void End(String order) {

		if (order.equals("lost")) {
			Ttext.setText("you have lost");
		} else {
			Ttext.setText("you have won");
		}

		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
