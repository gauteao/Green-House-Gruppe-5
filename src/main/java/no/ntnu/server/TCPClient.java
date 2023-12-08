package no.ntnu.server;

import java.io.*;
import java.net.*;

public class TCPClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final String SERVER_IP = "localhost"; // Replace with your server's IP address
    private final int SERVER_PORT = 6969; // Replace with your server's port number

    public TCPClient() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);

            // Create input and output streams for communication
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Start a thread for reading incoming messages from the server
            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Received message from server: " + message);
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

    // Method to send a message to the server
    public void sendMessage(String message) {
        out.println(message);
    }

    // Method to close the client socket and associated streams
    public void closeClient() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        client.sendMessage("Hello from the client!");
        // Add more test messages or logic here
        // client.sendMessage("Another message");
        // ...
        // Remember to close the client when done testing
        // client.closeClient();
    }
}

