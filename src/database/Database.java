package hist.drtablet.database;


import hist.drtablet.data.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Database {
	

    private Connection connection;
    private String databaseName;

	/* Initializes connection to the database */
	public Database() {
		try {
			String databaseDriver ="org.apache.derby.jdbc.ClientDriver";
			Class.forName(databaseDriver).newInstance();
			databaseName = "jdbc:derby://localhost:1527/drtabletDB";
		} catch (Exception sql) {
			System.out.println("DataSourceError: " + sql);
		}
	}

	/* Connects to the database */
	public void connect() {
		try {
			connection = DriverManager.getConnection(databaseName, "user","pass");
		} catch (SQLException sqle) {
			System.out.println("Connection Error: " + sqle);
		}
	}

	/* Disconnects from the database */
	public void disconnect() {
		Cleaner.closeConnection(connection);
	}
	
	
	/**
	 * This method retrieves events based on the parameters passed to it beneath.
	 * 
	 * @param patients, this is a list over the patients you want to retrieve events for
	 * @param eventIDs, this is a list over the events you currently have retrieved. 
	 *		    This is passed to the method to prevent duplication.
	 */
	
	public ArrayList<Event> getEvents(ArrayList<Patient> patients, ArrayList<Integer> eventIDs) {
	    ArrayList<Event> events = new ArrayList<Event>();
	    PreparedStatement prpstm = null;
	    ResultSet res = null;
	    connect();
	    try {
		String setning = "SELECT  * FROM event WHERE ";
		int hitCounter = 0;
		if (patients == null || patients.isEmpty()) return null;
		for (Patient patient : patients) {
		    if (hitCounter == 0) {
			setning += "( pid = ?";
		    }
		    else {
			setning += " OR pid = ?";
		    }
		    hitCounter++;
		}
		setning+=") ";
		if (eventIDs != null) {
		    setning += " AND (eid <> ? ";
		    for (int i = 1; i<eventIDs.size(); i++) {
			setning+=" AND eid <> ? ";
		    }
		    setning+= ")";
		}
		setning += " ORDER BY event_time DESC";
		System.out.println(setning);
		prpstm = connection.prepareStatement(setning);
		int prepareCounter = 1;
		
		for (Patient patient : patients) { 
		    prpstm.setString(prepareCounter, patient.getPid());
		    prepareCounter++;
		}
		if (eventIDs != null) {
		    for (Integer eventID : eventIDs) {
			prpstm.setInt(prepareCounter, eventID);
			prepareCounter++;
		    }
		    
		}
		
		
		
		res = prpstm.executeQuery();
		while(res.next()){
		    int eid = res.getInt("eid");
		    String pid = res.getString("pid");
		    int flag = 0;
		    int categoryRes = res.getInt("category");
		    long eventTime = res.getLong("event_time");
		    String title = res.getString("title");
		    String description = res.getString("description");
		    String pictureLink = res.getString("picture_link");
		    Event e = new Event(eid,pid,flag,categoryRes,eventTime,title,description,pictureLink);
		    events.add(e);
		   
		}		
		
		
		
	    } catch (Exception e) {
		Cleaner.writeMessage(e, "@getEvents()");
	    } finally {
			Cleaner.closePreparedStatement(prpstm);
			Cleaner.closeResultSet(res);
			disconnect();
			return events;
	    }
		
		
	   
	}
	
	/**
	 * This method returns all of the patients currently registered in the database. 
	 */
	public ArrayList<Patient> getPatientList() {
	    ArrayList<Bedpost> bedposts = getAllBedposts();
	    ArrayList<Patient> patients = new ArrayList<Patient>();
	    PreparedStatement prpstm = null;
	    ResultSet res = null;
	    connect();
	    try {
		prpstm = connection.prepareStatement("SELECT * FROM patient ORDER BY lastname DESC, firstname DESC");
		res = prpstm.executeQuery();
		while(res.next()) {
		    System.out.println("res.next()");
		   Bedpost bedpost = null;
		   int bpid = 0;
		   bpid = res.getInt("bpid");
		   if (bpid != 0) {
		       boolean found = false;
		       int index = 0;
		       while(!found && index < bedposts.size()) {
			   if (bpid == bedposts.get(index).getBpid()) {
			       bedpost = bedposts.get(index);
			       found = true;
			   }
			   index++;
		       }
		   }		    
		   Patient p = new Patient (res.getString("pid"), res.getString("firstname"),
			    res.getString("lastname"), res.getString("address"), res.getString("postal_code"),
			    null,res.getString("phonenumber"),
			    res.getString("patient_journal"), bedpost);
		   patients.add(p);
		}
		for (Patient p : patients) {
		    if (p.getPostalCode() != null) {
			prpstm = connection.prepareStatement("SELECT postal_address FROM postal_address WHERE postal_code = ?");
			prpstm.setString(1,p.getPostalCode());
			res = prpstm.executeQuery();
			res.next();
			p.setPostalAddress(res.getString("postal_address"));
		    }
		}
		System.out.println("HEIA");
		
	    }catch (Exception sqle) {
		Cleaner.writeMessage(sqle, "@getPatientList()");
	    }finally {
		Cleaner.closePreparedStatement(prpstm);
		Cleaner.closeResultSet(res);
		disconnect();
		return patients;
	    }
	}
	
	 
	/**
	 * This method returns all the departments currently registered in the database.
	 */
	
	public ArrayList<Department> getDepartments() {
	    PreparedStatement prpstm = null;
	    ResultSet res = null;
	    ArrayList<Department> departmetns = new ArrayList<Department>();
	    connect();
	    try {
		prpstm = connection.prepareStatement("SELECT * FROM department");
		res = prpstm.executeQuery();
		while (res.next()){
		    Department d = new Department(res.getInt("departmentID"), res.getString("name"));
		    departments.add(d);		    
		}
	    }catch (SQLException sqle) {
		Cleaner.writeMessage(sqle, "@getClinics()");
	    }finally {
		Cleaner.closePreparedStatement(prpstm);
		Cleaner.closeResultSet(res);
		disconnect();
		return clinics;
	    }
	}

    	/**
	 * This method returns the patient list for a given department.
	 */
	public ArrayList<Patient> getPatientList(int department_id) {
	    ArrayList<Patient> patients = new ArrayList<Patient>();
	    PreparedStatement prpstm = null;
	    ResultSet res = null;
	    connect();
	    try {
		prpstm = connection.prepareStatement("SELECT * FROM patient WHERE department_id = ? ORDER BY lastname DESC, firstname DESC");
		prpstm.setString(1,department_id);
        res = prpstm.executeQuery();
		while (res.next()){
		    Patient p = new Patient(res.getInt("patient_id"), res.getString("firstname"), res.getString("lastname"), res.getString("social_security_number"), res.getBytes("picture"), res.getInt("picture_offset"));
		    patients.add(p);		    
		}
	    }catch (SQLException sqle) {
		Cleaner.writeMessage(sqle, "@getClinics()");
	    }finally {
		Cleaner.closePreparedStatement(prpstm);
		Cleaner.closeResultSet(res);
		disconnect();
		return patients;
	    }
	}

    	

    	/**
	 * This method returns all the clinics currently registered in the database.

	
	public ArrayList<Clinic> getClinics() {
	    PreparedStatement prpstm = null;
	    ResultSet res = null;
	    ArrayList<Clinic> clinics = new ArrayList<Clinic>();
	    connect();
	    try {
		prpstm = connection.prepareStatement("SELECT * FROM clinic");
		res = prpstm.executeQuery();
		while (res.next()){
		    Clinic c = new Clinic(res.getInt("cid"), res.getString("clinic_name"));
		    clinics.add(c);		    
		}
	    }catch (SQLException sqle) {
		Cleaner.writeMessage(sqle, "@getClinics()");
	    }finally {
		Cleaner.closePreparedStatement(prpstm);
		Cleaner.closeResultSet(res);
		disconnect();
		return clinics;
	    }
	}
}
