package Deployment;

import Node.sensorNode;


public interface Deployment {

    public void deployOnContiki(sensorNode node);
    public void deployOnTOS(sensorNode node);    
    
}
