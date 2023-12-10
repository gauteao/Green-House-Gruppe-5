package no.ntnu.messages;

import java.nio.ByteBuffer;

public class ActuatorCommandMessage implements Message {
    private MessageType messageType;
    private int nodeId;
    private int actuatorId;
    private boolean commandValue;

    public ActuatorCommandMessage() {
        this.messageType = MessageType.ACTUATOR_COMMAND;
    }

    // Constructors, getters, setters, and other necessary methods...
    // Implement here...

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getActuatorId() {
        return actuatorId;
    }

    public void setActuatorId(int actuatorId) {
        this.actuatorId = actuatorId;
    }

    public boolean getCommandValue() {
        return commandValue;
    }

    public void setCommandValue(boolean commandValue) {
        this.commandValue = commandValue;
    }

    @Override
    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(getSize());
        buffer.put((byte) messageType.ordinal());
        buffer.putInt(nodeId);
        buffer.putInt(actuatorId);
        buffer.put((byte) (commandValue ? 1 : 0));
        return buffer.array();
    }

    @Override
    public int getSize() {
        // Calculate the size of the message based on the fields
        return Byte.BYTES + (Integer.BYTES * 2) + Byte.BYTES;
    }
}
