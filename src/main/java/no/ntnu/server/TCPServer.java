package no.ntnu.server;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public TCPServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for clients...");

            // Accept client connections
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // Setup input and output streams for communication with the client
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Start a thread for reading incoming messages from the client
            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Received message from client: " + message);
                        // Process the received message here
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToClient(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    public void stopServer() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int portNumber = 6969; // Replace with your desired port number
        TCPServer server = new TCPServer(portNumber);

        // Example sending message to the connected client
        server.sendMessageToClient("Hello from the server!");

        // Remember to stop the server when done
        // server.stopServer();
    }
}
