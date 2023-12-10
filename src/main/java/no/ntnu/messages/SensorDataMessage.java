package no.ntnu.messages;

import java.nio.ByteBuffer;

public class SensorDataMessage implements Message {
    private final MessageType messageType = MessageType.SENSOR_DATA; // Presuming MessageType enum exists
    private final int nodeId;
    private final double temperature;
    private final double humidity;

    public SensorDataMessage(int nodeId, double temperature, double humidity) {
        this.nodeId = nodeId;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public int getNodeId() {
        return nodeId;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(24); // Assuming 8 bytes per double and 4 bytes for int
        buffer.putInt(nodeId);
        buffer.putDouble(temperature);
        buffer.putDouble(humidity);
        return buffer.array();
    }

    @Override
    public int getSize() {
        return 24; // Size of the message in bytes
    }
}
