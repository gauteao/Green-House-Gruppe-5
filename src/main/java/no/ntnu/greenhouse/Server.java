package no.ntnu.greenhouse;

import java.io.*;
import java.net.*;
import java.util.Date;


public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    public void startServer(){

        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
 
                System.out.println("New client connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            } 
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
