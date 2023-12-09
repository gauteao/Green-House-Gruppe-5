package no.ntnu.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import no.ntnu.controlpanel.CommunicationChannel;
import no.ntnu.controlpanel.ControlPanelLogic;
import no.ntnu.tools.Logger;

public class SocketCommunicationChannel implements CommunicationChannel{

    private ControlPanelLogic logic;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public SocketCommunicationChannel(ControlPanelLogic logic, Socket socket,
    ObjectOutputStream outputStream, ObjectInputStream inputStream) {
    this.logic = logic;
    this.socket = socket;
    this.outputStream = outputStream;
    this.inputStream = inputStream;
}
    

@Override
public void sendActuatorChange(int nodeId, int actuatorId, boolean isOn) {
    // Implement the logic to send actuator change command to the server
    // For example, you can create a Command object and send it through the socket
    // outputStream.writeObject(new ActuatorChangeCommand(nodeId, actuatorId, isOn));
}

@Override
public boolean open() {
    // The socket connection is already established when creating SocketCommunicationChannel
    // You can perform any additional setup or return true if no additional setup is needed
    return true;
}

// Additional methods and overrides as needed

public void close() {
    try {
        if (outputStream != null) {
            outputStream.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        // Note: Do not close the socket here, as it may be used by other components
    } catch (IOException e) {
        Logger.error("Error closing communication channel: " + e.getMessage());
    }
}
}
