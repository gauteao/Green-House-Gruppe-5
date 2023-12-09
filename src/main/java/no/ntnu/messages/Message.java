package no.ntnu.messages;

import java.io.Serializable;

public interface Message extends Serializable {
    // Get the message type
    MessageType getMessageType();

    // Convert the message to a byte array for sending
    byte[] toByteArray();
    
    // Get the size of the message in bytes
    int getSize();
}
