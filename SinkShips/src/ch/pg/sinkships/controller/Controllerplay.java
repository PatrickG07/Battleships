package ch.pg.sinkships.controller;

import ch.pg.sinkships.controller.client.Client;
import ch.pg.sinkships.controller.server.Server;
import ch.pg.sinkships.model.Game;
import ch.pg.sinkships.view.StartSinkShips;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * The Class for The hole Game
 * 
 * @author PatrickG07
 */
public class Controllerplay {

	public static int X, Y;

	private static boolean server;

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

	Node nodes;

	private static Image picture;

	/**
	 * For reseting the Ships health. Showing your Ships on the left side.
	 * It starts The Server or Connecting to one as a Client.
	 */
	@FXML
	public void initialize() {

		// Starting Server or Connecting to a Server
		if (Game.actualTable == "table1") {
			StartServer();
			server = true;
		} else if (Game.actualTable == "table2") {
			Connect();
			griden.setDisable(true);
			server = false;
		}

		// show where the ships are
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

		// Reseting the health
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
	 * get kicked Item id and checks if one Ship is on this Tile and sets the
	 * correct Image to the ImageView
	 * 
	 * it has to be change later with the Server from table1 to table2
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
	 * Column)
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
	
	private void getNodeFromGridPaneEnemy() {
		for (Node node : griden.getChildren()) {
			if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
				if (GridPane.getColumnIndex(node) == Y && GridPane.getRowIndex(node) == X) {
					nodes = node;
				}
			}
		}
	}

	public void reciver(String order) {
		
		String trimmed = order.trim();

		if (trimmed.equals("hit") || trimmed.equals("miss")) /* if you hit an enemy ship */ {
			getNodeFromGridPaneEnemy();
			if (trimmed.equals("hit")) {
				picture = new Image("/ch/pg/sinkships/sources/hit.jpg");
				griden.setDisable(false);
			} else {
				picture = new Image("/ch/pg/sinkships/sources/dot.jpg");
				griden.setDisable(true);
			}

			ImageView Enemy = (ImageView) nodes;

			Enemy.setImage(picture);
		} else if (trimmed.equals("won")) /* if you won / destroyed all enemy ships */ {
			
		}
		else /* hetting an X and Y value to chech if enemy hit */ {
			
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
			}
			getNodeFromGridPaneYou();
			ImageView you = (ImageView) nodes;

			Game.table1.setCheckforhit(false);

			you.setImage(picture);

			if (Game.table1.Ship1.getDestroyd() == true && Game.table1.Ship2.getDestroyd() == true
					&& Game.table1.Ship3.getDestroyd() == true && Game.table1.Ship4.getDestroyd() == true
					&& Game.table1.Ship5.getDestroyd() == true) {
				sendMessage("lost");

				trimmed = "won";
						
				End(trimmed);
			}
		}
	}

	/**
	 * Sending Messages mostly Numbers (2-2), hits, misses or won
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
	 * Receiving Messages from the other Player
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
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * if one Player destroy all Ships it will display as an win for this Player
	 */
	private void End(String order) {

		String title, text;

		if (order.equals("lost")){
			title = "Lost";
			text = "The other Player won";
		} else {
			title = "Winner!";
			text = "You Wonn";
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);

		alert.setTitle(title);
		alert.setHeaderText(text);
		alert.showAndWait();

		StartSinkShips.loadScene("/ch/pg/sinkships/view/Main");
	}
}
