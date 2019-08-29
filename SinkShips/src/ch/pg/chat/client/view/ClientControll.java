package ch.pg.chat.client.view;

import ch.pg.chat.client.conn.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientControll {
	
	@FXML
	protected TextField inputmessage;
	
	@FXML
	protected TextArea outputMessage;
	
	@FXML
	protected void sendMessage(ActionEvent e) {
		try{
            Client.getInstance().sendMessage(inputmessage.getText());
            outputMessage.appendText("Client: " + inputmessage.getText() + "\n");
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
	}
	
	@FXML
	protected void Connect(ActionEvent e) {
		try{
            Client.getInstance().connectToServer(this);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
	}

    public void onMessageReceived(String message) {
    	outputMessage.appendText("Server: " + message + "\n");
    }
}
