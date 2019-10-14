package ch.pg.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ch.pg.sinkships.model.Game;
import javafx.fxml.FXML;

public class Sender {

	Socket client;
	PrintWriter writer;
	BufferedReader reader;

	int zaeler = 4;
	int Person = 0;

	@FXML
	public void initialize() {
		connectToServer();

		Thread t = new Thread(new MessagesFromServerListener());
		t.start();
	}

	public boolean connectToServer() {
		try {
			client = new Socket("127.0.0.1", 5555);
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writer = new PrintWriter(client.getOutputStream());
			appendTextMessages("Netzwerkverbindung hergestellt");

			return true;
		} catch (Exception e) {
			appendTextMessages("Netzwerkverbindung konnte nicht hergestellt werden");
			e.printStackTrace();

			return false;
		}
	}

	public void sendMessageToServer(int X, int Y) {
		writer.println("XXX" + X);
		writer.println("YYY" + Y);
		writer.flush();
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

	public class MessagesFromServerListener implements Runnable {
		public void run() {
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					appendTextMessages(message);
				}
			} catch (IOException e) {
				appendTextMessages("Nachricht konnte nicht empfangen werden!");
				e.printStackTrace();
			}
		}
	}
}