/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.pg.sinkships.controller.client;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import ch.pg.sinkships.controller.Controllerplay;
import ch.pg.sinkships.model.Game;

/**
 * Client Side Connection
 */
public class Client {
    private static Client instance;
    private Socket socketConn;
    private InputStreamReader isr;
    private OutputStreamWriter osw;
    
    private Controllerplay mi;
    
    public static Client getInstance(){
        if(instance == null){
            instance = new Client();
        }
        return instance;
    }
    
    /**
     * Create a Connection to the Server with IP and Port 3535
     * 
     * @param controllerplay
     * @throws Exception
     */
    public void connectToServer(Controllerplay controllerplay) throws Exception{
        this.mi = controllerplay;
        System.out.println("Connection to server...");
        String ip = Game.table1.ip;
        if(ip == "") {
        	ip = "localhost";
        }
        System.out.println("IP: " + ip);
        socketConn = new Socket(ip, 3535);
        isr = new InputStreamReader(socketConn.getInputStream());
        osw = new OutputStreamWriter(socketConn.getOutputStream());
        System.out.println("Conneted to server");
        sendMessage("start");
        listenForMessages();
    }
    
    /**
     * For receiving messages
     */
    public void listenForMessages(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        char[] charMessage = new char[1024];
                        if(isr.read(charMessage, 0, charMessage.length) != -1){
                            String message = new String(charMessage);
                            mi.onMessageReceived(message);
                            System.out.println(message);
                        }
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
            }
        }).start();
    }
    
    /**
     * For Sending Messages
     * 
     * @param message
     * @throws Exception
     */
    public void sendMessage(String message)throws Exception{
        osw.write(message);
        osw.flush();
    }
}
