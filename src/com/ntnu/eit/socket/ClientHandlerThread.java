package com.ntnu.eit.socket;

import com.ntnu.eit.database.Database;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class ClientHandlerThread extends Thread {

	Socket s = null;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	Database db = null;

	public ClientHandlerThread(Socket s) {
		this.s = s;

	}

	/**
	 * This methods returns an arrayList of tasks, patients or departments based
	 * on which socketobject it receives from the client.
	 */
	@Override
	public void run() {
		ArrayList<Object> objects = null;

		/* Get timestamp for log */
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date d = new Date();
		String date = dateFormat.format(d);

		/* Try to fetch socket object and respond */
		try {
			db = new Database();
			objects = new ArrayList<Object>();
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			Object o = in.readObject();

			/* TaskSocketObject */
			if (o instanceof TaskSocketObject) {
				System.out.println(date + " - TaskSocketObject recieved");
				TaskSocketObject tso = (TaskSocketObject) o;
				int patientId = tso.getPatientId();
				if (tso.getExecutedTasks() != null
						&& !tso.getExecutedTasks().isEmpty()) {
					// TODO update database
				}
				objects.addAll(db.getTasks(patientId));

				/* PatientSocketObject */
			} else if (o instanceof PatientSocketObject) {
				System.out.println(date + " - PatientSocketObject recieved");
				PatientSocketObject pso = (PatientSocketObject) o;
				int departmentId = pso.getDepartmentId();
				objects.addAll(db.getPatientList(departmentId));

				/* PictureSocketObject */
			} else if (o instanceof PictureSocketObject) {
				System.out.println(date + " - PictureSocketObject recieved");
				PictureSocketObject pso = (PictureSocketObject) o;
				int patientId = pso.getPatientId();
				objects.add(db.getPicture(patientId));

			} else if (o instanceof DepartmentSocketObject) {
				objects.addAll(db.getDepartments());
			}

			System.out.println("objectSize: " + objects.size());
			out.writeObject(objects);
			out.flush();

		} catch (Exception e) {
			System.out.println(date
					+ " - ERROR: Socketcommunication with the server failed: "
					+ e);
		} finally {
			try {
				out.close();
				in.close();
				s.close();
			} catch (Exception e1) {
				System.out.println(date
						+ " - ERROR: Closing of streaks and/or socket failed: "
						+ e1);
			}
		}
	}

}
