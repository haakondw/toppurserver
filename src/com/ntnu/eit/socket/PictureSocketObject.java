package com.ntnu.eit.socket;

import java.io.Serializable;


public class PictureSocketObject implements Serializable{
  

	private static final long serialVersionUID = 5936434784692547982L;
	private int patientId;

	public PictureSocketObject(int patientId) {
		
		this.patientId = patientId;
		
	}

	public void setPatientId(int patientId){
		this.patientId = patientId;
	}
	public int getPatientId(){
		this.patientId = patientId;
	}

    
    
    
}
