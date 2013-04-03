package com.ntnu.eit.socket;

import hist.drtablet.data.*;
import hist.drtablet.database.Database;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;



class ClientHandlerThread extends Thread{
    
    Socket s = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    Database db = null;
    
    
    public ClientHandlerThread(Socket s) {
	this.s = s;
		
    }
    /**
     * This methods returns an arrayList of tasks, patients or departments
     * based on which socketobject it receives from the client. 
     */
    @Override
    public void run() {
	ArrayList<Object> objects = null;
	try {
	    db = new Database();
	    objects = new ArrayList<Object>();
	    out = new ObjectOutputStream(s.getOutputStream());
	    in = new ObjectInputStream(s.getInputStream());
	    Object o = in.readObject();

	    /* Get timestamp for log */
	   	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date();
		String date = dateFormat.format(date);

	    /* TaskSocketObject */
	    if (o instanceof TaskSocketObject) {
		System.out.println(date + " - TaskSocketObject recieved");
		TaskSocketObject tso = (TaskSocketObject)o;
		int patientId = tso.getPatientId();
		if(tso.getExecutedTasks != null && !tso.getExecutedTasks.isEmpty()){
			//TODO update database
		}
		objects.addAll(db.getTasks(patientId));

		/* PatientSocketObject */  
	    }else if (o instanceof PatientSocketObject) {
		System.out.println(date + " - PatientSocketObject recieved");
		PatientSocketObject pso = (PatientSocketObject)o;
		int departmentId = pso.getDepartmentId();
		objects.addAll(db.getPatientList(departmentId));

		/* PictureSocketObject */
	    }else if (o instanceof PictureSocketObject) {
		System.out.println(date + " - PictureSocketObject recieved");
		PictureSocketObject pso = (PictureSocketObject)o;
		int patientId = pso.getPatientId();
		objects.addAll(db.getPicture(patientId));	
	    }
	    System.out.println("objectSize: "+objects.size());
	    out.writeObject(objects);
	    out.flush();
		
	}catch(Exception e) {
	    System.out.println("Socketcommunication with the server failed: "+e);
	}
	finally {
	    try {
		out.close();
		in.close();
		s.close();
	    }
	    catch (Exception e1) {
		System.out.println("Closing of streaks and/or socket failed: "+e1);
	    }
	}
    }
    
}
