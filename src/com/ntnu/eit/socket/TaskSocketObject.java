package com.ntnu.eit.socket;

import java.io.Serializable;
import java.util.ArrayList;


public class TaskSocketObject implements Serializable{
  
    private static final long serialVersionUID = -1460051194874732469L;
    private int patientId;
    List<Integer> executedTasks;

    public TaskSocketObject(int patientId, List executedTasks) {
    this.patientId = patientId;
    this.executedTasks = new ArrayList<Integer>(executedTasks);
    }
    
    
    public void setPatientId(int patientId){
        this.patientId = patientId;
    } 
    public int getPatientId(){
        return patientId;
    }

    public void setExecutedTasks(List executedTasks){
        this.executedTasks = new ArrayList<Integer>(executedTasks);
    }
    public List<Integer> getExecutedTasks(){
        return executedTasks;
    }
    
}
