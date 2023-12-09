package no.ntnu.controlpanel;

public class TCPCommunicationChannelAdapter implements CommunicationChannel {
    private final TCPCommunicationChannel tcpChannel;

    public TCPCommunicationChannelAdapter(TCPCommunicationChannel tcpChannel) {
        this.tcpChannel = tcpChannel;
    }

    @Override
    public void sendActuatorChange(int nodeId, int actuatorId, boolean isOn) {
        tcpChannel.sendActuatorChange(nodeId, actuatorId, isOn);
    }

    @Override
    public boolean open() {
        return tcpChannel.open();
    }
}
