package no.ntnu.command;

import java.io.Serializable;

public class ActuatorChangeMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private int nodeId;
    private int actuatorId;
    private boolean isOn;

    public ActuatorChangeMessage(int nodeId, int actuatorId, boolean isOn) {
        this.nodeId = nodeId;
        this.actuatorId = actuatorId;
        this.isOn = isOn;
    }

    // Getters and setters for nodeId, actuatorId, and isOn
    public int getNodeId() {
        return this.nodeId;
    }

    public int getActuatorId() {
        return this.actuatorId;
    }

    public boolean getIsOn() {
        return this.isOn;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setActuatorId(int actuatorId) {
        this.actuatorId = actuatorId;
    }

    public void toggleIsOn(boolean isOn) {
        this.isOn = isOn;
    }
    // (These methods can be generated by your IDE or written manually)
}
