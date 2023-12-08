package no.ntnu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A TCP server, handles multiple clients.
 */
public class TCPServer {
  public static final int TCP_PORT = 1235;
  private ServerSocket serverSocket;
  private volatile boolean isRunning;

  public static void main(String[] args) {
    TCPServer server = new TCPServer();
    server.run();
  }

  private void run() {
    if (openListeningSocket()) {
      isRunning = true;
      while (isRunning) {
        try {
          Socket clientSocket = serverSocket.accept();
          // Handle each client connection is a new thread
          new Thread(new TCPClientHandler(clientSocket)).start();
        } catch (IOException e) {
          System.err.println("Error accepting client connection: " + e.getMessage());
        }
      }
    }

    System.out.println("TCPServer exiting...");
  }


  /**
   * Open a listening TCP socket.
   *
   * @return True on success, false on error.
   */
  private boolean openListeningSocket() {
    boolean success = false;
    try {
      serverSocket = new ServerSocket(TCP_PORT);
      System.out.println("TCPServer listening on port " + TCP_PORT);
      success = true;
    } catch (IOException e) {
      System.err.println("Could not open a listening socket on port " + TCP_PORT
          + ", reason: " + e.getMessage());
    }
    return success;
  }

  private Socket acceptNextClient() {
    Socket clientSocket = null;
    try {
      clientSocket = serverSocket.accept();
    } catch (IOException e) {
      System.err.println("Could not accept the next client: " + e.getMessage());
    }
    return clientSocket;
  }

  /**
   * Shut down the server.
   */
  public void shutdown() {
    isRunning = false;
    try {
      if (serverSocket != null) {
        serverSocket.close();
      }
    } catch (IOException e) {
      System.err.println("Error while closing the server socket: " + e.getMessage());
    }
  }

}