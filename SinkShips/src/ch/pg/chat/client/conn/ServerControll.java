/**package ch.pg.chat.client.conn;

import ch.pg.sinkships.controller.Controllerplay;

public class ServerControll {

	public static void sendMessage(String order) {
		try {
            Server.getInstance().sendMessage(order);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
	}
	
	public static void StartServer() {
		try {
            Server.getInstance().startServer(null);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
	}

    public void onMessageReceived(String message) {
    	Controllerplay.getInstance().sender(message);
    }
}**/