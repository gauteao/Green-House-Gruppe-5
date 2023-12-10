package no.ntnu.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private ServerSocket serverSocket;
    private volatile boolean isRunning = true;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void startServer(){

        try {
            System.out.println("Listening to port: " + serverSocket.getLocalPort());
            while(isRunning && !serverSocket.isClosed()){
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                serverSocket.accept();
                System.out.println("A new client has connected");

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e){
           if(!serverSocket.isClosed()){
            e.printStackTrace();
           }
        } finally {
             closeServerSocket();
        }
    }

        public void closeServerSocket(){
            try{
                isRunning = false;
                if(serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public static void main(String[] args) throws IOException {
            
            ServerSocket serverSocket = new ServerSocket(55723);
            Server server = new Server(serverSocket);
            server.startServer();
        }



}
