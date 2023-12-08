package no.ntnu.server;

import static no.ntnu.server.TCPServer.TCP_PORT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import no.ntnu.command.Command;
import no.ntnu.command.VersionCommand;

/**
 * TCP Client.
 */
public class TCPClient {
  private static final String SERVER_HOST = "localhost";
  private Socket socket;
  private BufferedReader socketReader;
  private ObjectOutputStream objectWriter;


  /**
   * Run the web client.
   *
   * @param args Command line arguments. Not used.
   */
  public static void main(String[] args) {
    TCPClient client = new TCPClient();
    client.run();
  }

  private void run() {
    if (connect()) {
      sendAndReceive(new VersionCommand());
      disconnect();
    }
    System.out.println("Exiting...");
  }

  public void sendAndReceive(Command command) {
    if (sendToServer(command)) {
      String response = receiveOneLineFromServer();
      if (response != null) {
        System.out.println("Server's response: " + response);
      }
    }
  }

  /**
   * Establish a connection to a TCP server (web server).
   *
   * @return True on success, false on error.
   */
  public boolean connect() {
    boolean success = false;
    try {
      socket = new Socket(SERVER_HOST, TCP_PORT);
      socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      objectWriter = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("Connection established");
      success = true;
    } catch (IOException e) {
      System.err.println("Could not connect to the server: " + e.getMessage());
    }
    return success;
  }

  /**
   * Send a message to the TCP server.
   * We assume that the connection is already established.
   *
   * @param message The message to send
   * @return True when the message is successfully sent, false on error.
   */
  public boolean sendToServer(Command message) {
    boolean sent = false;
    try {
      objectWriter.writeObject(message);
      sent = true;
    } catch (Exception e) {
      System.err.println("Error while sending the message: " + e.getMessage());
    }
    return sent;
  }

  /**
   * Receive one line of text from the server (the TCP socket).
   *
   * @return The received line or null on error.
   */
  private String receiveOneLineFromServer() {
    String response = null;
    try {
      response = socketReader.readLine();
    } catch (IOException e) {
      System.err.println("Error while receiving data from the server: " + e.getMessage());
    }
    return response;
  }

  /**
   * Close the TCP connection.
   */
  public void disconnect() {
    try {
      if (socket != null) {
        socket.close();
        System.out.println("Socket closed");
      } else {
        System.err.println("Can't close a socket which has not been open");
      }
    } catch (IOException e) {
      System.err.println("Could not close the socket: " + e.getMessage());
    }
  }
}