package ch.pg.sinkships.controller;

import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import ch.pg.sinkships.controller.client.Client;
import ch.pg.sinkships.controller.server.Server;
import ch.pg.sinkships.model.Game;
import ch.pg.sinkships.model.Ship;
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
				if (Game.table1.checkforhit(x, y) == true) {
					picture = new Image("/ch/pg/sinkships/sources/pictures/ship.jpg");
					ImageView you = (ImageView) nodes;
					you.setImage(picture);
				}
			}
		}
		
		int health = 2;
		for(Ship i : Game.table1.ship) {
			i.setDestroyed(false);
			
			i.setHealth(health);
			health++;
		}
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
				picture = new Image("/ch/pg/sinkships/sources/pictures/hit.jpg");
				Sound("/ch/pg/sinkships/sources/sounds/Explosion.wav");
				griden.setDisable(false);
			} else {
				picture = new Image("/ch/pg/sinkships/sources/pictures/dot.jpg");
				Sound("/ch/pg/sinkships/sources/sounds/Splash.wav");
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

			if (Game.table1.checkforhit(X, Y) == true) {
				picture = new Image("/ch/pg/sinkships/sources/pictures/hit.jpg");
				Sound("/ch/pg/sinkships/sources/sounds/Explosion.wav");
				sendMessage("hit");
			} else {
				picture = new Image("/ch/pg/sinkships/sources/pictures/dot.jpg");
				Sound("/ch/pg/sinkships/sources/sounds/Splash.wav");
				sendMessage("miss");
				griden.setDisable(false);
				Ttext.setText("your turn");
			}
			getNodeFromGridPaneYou();
			ImageView you = (ImageView) nodes;

			you.setImage(picture);

			if (Game.table1.ship[0].getDestroyed() == true && Game.table1.ship[1].getDestroyed() == true
					&& Game.table1.ship[2].getDestroyed() == true && Game.table1.ship[3].getDestroyed() == true
					&& Game.table1.ship[4].getDestroyed() == true) { 

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
	 * Plays the sound of Hit or Miss
	 * 
	 * @param url
	 */
	private static void Sound(String url) {
		 try {
			 Clip clip = AudioSystem.getClip();
			 AudioInputStream inputStream = AudioSystem.getAudioInputStream(Controllerplay.class.getResourceAsStream(url));
			 clip.open(inputStream);
			 clip.start(); 
		 } catch (Exception e) {
			 System.err.println(e.getMessage());
		 }
	}

	/**
	 * if one Player destroy all Ships it will display as an win for that Player
	 * 
	 * @param order
	 */
	private void End(String order) {

		if (order.equals("lost")) {
			Ttext.setText("you have lost");
			Sound("/ch/pg/sinkships/sources/sounds/Lost.wav");
		} else {
			Ttext.setText("you have won");
			Sound("/ch/pg/sinkships/sources/sounds/Victory.wav");
		}

		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
