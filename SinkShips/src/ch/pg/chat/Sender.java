package ch.pg.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import ch.pg.sinkships.model.Game;
import javafx.fxml.FXML;

public class Sender {

	Socket client;
	DataOutputStream writer;
	DataInputStream reader;

	int zaeler = 4;
	int Person = 0;

	@FXML
	public void initialize() {
		connectToServer();
	}

	public boolean connectToServer() {
		try {
			client = new Socket("localhost", 1337);
			reader = new DataInputStream(client.getInputStream());
			writer = new DataOutputStream(client.getOutputStream());
			System.out.println("Nethwerk hergestellt");
			
			return true;
		} catch (Exception e) {
			System.out.println("Netzwerk fehler");
			e.printStackTrace();

			return false;
		}
	}

	public void sendMessageToServer(int X, int Y) {
		try {
			writer.writeUTF("X and Y are " + X +" "+Y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//writer.println("XXX" + X);
		//writer.println("YYY" + Y);
	}

	public void appendTextMessages(String message) {
		if(message == "p1") {
			Game.actualTable = "table1";
		}else if(message == "p2") {
			Game.actualTable = "table2";
		}
		if(zaeler < 4) {
			System.out.println(message + "\n");
			zaeler++;
		} else {
			if (zaeler == 6) {
				zaeler = 1;
			} else {
				zaeler++;
			}
		}
	}
}