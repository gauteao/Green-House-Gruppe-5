package no.ntnu.controlpanel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import no.ntnu.command.ActuatorChangeMessage;

public class TCPCommunicationChannel implements CommunicationChannel {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;

    @Override
    public void sendActuatorChange(int nodeId, int actuatorId, boolean isOn) {
        if (objectOutputStream != null) {
            try {
                ActuatorChangeMessage message = new ActuatorChangeMessage(nodeId, actuatorId, isOn);
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
            } catch (IOException e) {
                System.err.println("Error sending actuator change message: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean open() {
        try {
            // Establish a TCP connection to the server
            socket = new Socket("localhost", 12345);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            System.err.println("Error opening TCP communication channel: " + e.getMessage());
            return false;
        }
    }

    // Other methods for handling the TCP communication channel, such as closing it, etc.
}
