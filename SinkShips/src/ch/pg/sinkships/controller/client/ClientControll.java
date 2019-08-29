package ch.pg.sinkships.controller.client;
import ch.pg.sinkships.controller.Controllerplay;

public class ClientControll {
	Controllerplay Controller = new Controllerplay();
	
	public static void sendMessage(String test) {
		try{
			Client.getInstance().sendMessage(test);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
	}
	
	public static void Connect() {
		try{
            Client.getInstance().connectToServer(null);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
	}

	public void onMessageReceived(String message) {
		Controller.reciver(message);
    }
}
